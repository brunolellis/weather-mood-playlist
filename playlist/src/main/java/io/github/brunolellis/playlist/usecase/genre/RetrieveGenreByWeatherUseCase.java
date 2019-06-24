package io.github.brunolellis.playlist.usecase.genre;

import io.github.brunolellis.playlist.usecase.Weather;

public interface RetrieveGenreByWeatherUseCase {
    Genre retrieve(Weather weather);
}
