package io.github.brunolellis.spotify.ports.out;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import io.github.brunolellis.spotify.ports.out.exception.SpotifyUnavailableException;
import io.github.brunolellis.spotify.ports.out.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class SpotifyGateway {

    private final SpotifyProperties properties;
    private final Cache<Genre, SearchResponse> spotifyCache;

    private String basicAuth;

    @PostConstruct
    protected void postConstruct() {
        this.basicAuth = encode(properties);
    }

    public Mono<SearchResponse> searchTracksByGenre(Genre genre) {
        SearchResponse cachedResponse = spotifyCache.getIfPresent(genre);
        if (cachedResponse != null) {
            return Mono.just(cachedResponse);
        }

        return authorization()
                .flatMap(token -> search(token, genre));
    }

    private Mono<Authorization> authorization() {
        return WebClient.create(properties.getAuthorizationUrl())
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/token")
                        .build())
                .header("Authorization", "Basic " + basicAuth)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .onStatus(HttpStatus::isError, e -> Mono.error(new UnauthorizedException()))
                .bodyToMono(Authorization.class);

    }

    private Mono<SearchResponse> search(Authorization token, Genre genre) {
        return WebClient.create(properties.getApiUrl())
                        .get()
                        .uri(uriBuilder -> uriBuilder.path("/v1/search") // ?q=genre%3Arock&type=track
                                .queryParam("q", "genre:" + genre.getGenre())
                                .queryParam("type", "track")
                                .build())
                        .header("Authorization", "Bearer " + token.getAccessToken())
                        .retrieve()
                        .onStatus(HttpStatus::is5xxServerError, e -> Mono.error(new SpotifyUnavailableException()))
                        .bodyToMono(SearchResponse.class)
                        .doOnNext(response -> spotifyCache.put(genre, response));
    }

    private String encode(SpotifyProperties properties) {
        byte[] bytes = (properties.getClientId() + ":" + properties.getClientSecret()).getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

}
