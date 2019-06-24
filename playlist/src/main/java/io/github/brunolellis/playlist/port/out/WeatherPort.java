package io.github.brunolellis.playlist.port.out;

import io.github.brunolellis.playlist.usecase.Weather;

public interface WeatherPort {
    Weather findWeatherByCity(String city) throws CityNotFoundException;
}
