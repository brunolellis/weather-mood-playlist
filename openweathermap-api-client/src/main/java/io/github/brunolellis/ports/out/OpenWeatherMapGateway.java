package io.github.brunolellis.ports.out;

import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import io.github.brunolellis.playlist.usecase.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapGateway {

    private final OpenWeatherMapProperties properties;

    private String getCompatiblePath() {
        return "/data/2.5/weather";
    }

    public Mono<Weather> getTemperature(String city) {
        Mono<Weather> weather =
                WebClient.create(properties.getUrl())
                        .get()
                        .uri(uriBuilder -> uriBuilder.path(getCompatiblePath())
                                .queryParam("q", city)
                                .queryParam("units", "metric")
                                .queryParam("APPID", properties.getAppid())
                                .build())
                        .retrieve()
                        .onStatus(HttpStatus::isError, e -> Mono.error(new CityNotFoundException(city)))
                        .bodyToMono(OpenWeatherResponse.class)
                        .map(response -> new Weather(response.getTemperature().getCurrent()));

        return weather;
    }

    public Mono<Weather> getTemperature(double latitude, double longitude) {
        Mono<Weather> weather =
                WebClient.create(properties.getUrl())
                        .get()
                        .uri(uriBuilder -> uriBuilder.path(getCompatiblePath())
                                .queryParam("lat", latitude)
                                .queryParam("lon", longitude)
                                .queryParam("units", "metric")
                                .queryParam("APPID", properties.getAppid())
                                .build())
                        .retrieve()
                        .onStatus(HttpStatus::isError, e -> Mono.error(new CityNotFoundException(latitude, longitude)))
                        .bodyToMono(OpenWeatherResponse.class)
                        .map(response -> new Weather(response.getTemperature().getCurrent()));

        return weather;
    }

}
