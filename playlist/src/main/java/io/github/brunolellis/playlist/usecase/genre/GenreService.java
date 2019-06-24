package io.github.brunolellis.playlist.usecase.genre;

import io.github.brunolellis.playlist.usecase.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService implements RetrieveGenreByWeatherUseCase {

    private final List<GenreStrategy> genres;

    @Override
    public Genre retrieve(Weather weather) {
        return genres.stream()
                .filter(strategy -> strategy.accepts(weather))
                .findFirst()
                .get()
                .getGenre();
    }

}
