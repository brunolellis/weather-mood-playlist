package io.github.brunolellis.playlist.adapter.web;

import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import io.github.brunolellis.playlist.usecase.coordinates.CoordinatesQuery;
import io.github.brunolellis.playlist.usecase.coordinates.RetrievePlaylistByCoordinatesUseCase;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.city.CityQuery;
import io.github.brunolellis.playlist.usecase.city.RetrievePlaylistByCityUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/playlist")
@RequiredArgsConstructor
public class PlaylistResource {

    private static final Logger LOG = LoggerFactory.getLogger(PlaylistResource.class);

    private final RetrievePlaylistByCityUseCase retrievePlaylistByCityUseCase;
    private final RetrievePlaylistByCoordinatesUseCase retrievePlaylistByCoordinatesUseCase;

    private final CircuitBreaker circuitBreaker;

    @GetMapping("/city/{city}")
    public Mono<Playlist> getByCity(@PathVariable("city") String city) {
        return retrievePlaylistByCityUseCase.query(new CityQuery(city))
                .compose(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(e -> !(e instanceof CityNotFoundException), e -> playlistFallback(e));
    }

    @GetMapping("/coordinates")
    public Mono<Playlist> getByCoordinates(@RequestParam("lat") float latitude, @RequestParam("lon") float longitude) {
        return retrievePlaylistByCoordinatesUseCase.query(new CoordinatesQuery(latitude, longitude))
                .compose(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(exception -> playlistFallback(exception));
    }

    private Mono<Playlist> playlistFallback(Throwable exception) {
        LOG.warn("Circuit breaker opened - returning default playlist", exception);

        return Mono.just(new DefaultPlaylistGenerator().generate());
    }

}
