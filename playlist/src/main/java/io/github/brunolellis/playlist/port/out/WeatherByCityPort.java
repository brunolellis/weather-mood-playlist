package io.github.brunolellis.playlist.port.out;

import io.github.brunolellis.playlist.usecase.Weather;
import reactor.core.publisher.Mono;

public interface WeatherByCityPort {
    Mono<Weather> findWeatherByCity(String city) throws CityNotFoundException;
}
