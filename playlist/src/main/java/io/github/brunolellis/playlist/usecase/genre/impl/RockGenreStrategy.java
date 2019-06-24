package io.github.brunolellis.playlist.usecase.genre.impl;

import io.github.brunolellis.playlist.usecase.Weather;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import io.github.brunolellis.playlist.usecase.genre.GenreStrategy;
import org.springframework.stereotype.Component;

@Component
public class RockGenreStrategy implements GenreStrategy {

    @Override
    public boolean accepts(Weather weather) {
        return weather.getCurrentTemperature() >= 10.0 && weather.getCurrentTemperature() < 15.0;
    }

    @Override
    public Genre getGenre() {
        return Genre.ROCK;
    }

}
