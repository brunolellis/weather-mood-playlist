package io.github.brunolellis.openweathermap.ports.out;

import com.github.benmanes.caffeine.cache.Cache;
import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapGateway {

    private final OpenWeatherMapProperties properties;
    private final Cache<String, OpenWeatherResponse> weatherCache;

    public Mono<OpenWeatherResponse> getTemperature(String city) {
        OpenWeatherResponse cachedResponse = weatherCache.getIfPresent(city);
        if (cachedResponse != null) {
            return Mono.just(cachedResponse);
        }

        return WebClient.create(properties.getUrl())
                        .get()
                        .uri(uriBuilder -> uriBuilder.path(getPath())
                                .queryParam("q", city)
                                .queryParam("units", "metric")
                                .queryParam("APPID", properties.getAppid())
                                .build())
                        .retrieve()
                        .onStatus(HttpStatus::isError, e -> Mono.error(new CityNotFoundException(city)))
                        .bodyToMono(OpenWeatherResponse.class)
                        .doOnNext(response -> weatherCache.put(city, response));
    }

    public Mono<OpenWeatherResponse> getTemperature(double latitude, double longitude) {
        Mono<OpenWeatherResponse> weather =
                WebClient.create(properties.getUrl())
                        .get()
                        .uri(uriBuilder -> uriBuilder.path(getPath())
                                .queryParam("lat", latitude)
                                .queryParam("lon", longitude)
                                .queryParam("units", "metric")
                                .queryParam("APPID", properties.getAppid())
                                .build())
                        .retrieve()
                        .onStatus(HttpStatus::isError, e -> Mono.error(new CityNotFoundException(latitude, longitude)))
                        .bodyToMono(OpenWeatherResponse.class);

        return weather;
    }

    private String getPath() {
        return "/data/2.5/weather";
    }

}
