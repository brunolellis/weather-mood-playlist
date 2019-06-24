package io.github.brunolellis.playlist.usecase.genre;

import io.github.brunolellis.playlist.usecase.Weather;

public interface GenreStrategy {

    boolean accepts(Weather weather);
    Genre getGenre();

}
