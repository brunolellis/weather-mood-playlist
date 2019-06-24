package io.github.brunolellis.playlist.port.out;

import io.github.brunolellis.playlist.usecase.Weather;

public interface WeatherByCoordinatesPort {
    Weather findWeatherByCoordinates(double latitude, double longitude);
}
