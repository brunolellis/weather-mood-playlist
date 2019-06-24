package io.github.brunolellis.playlist.port.out;

import io.github.brunolellis.playlist.usecase.Weather;
import reactor.core.publisher.Mono;

public interface WeatherByCoordinatesPort {
    Mono<Weather> findWeatherByCoordinates(double latitude, double longitude);
}
