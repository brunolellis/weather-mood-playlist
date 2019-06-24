package io.github.brunolellis.playlist.usecase.genre;

import io.github.brunolellis.playlist.usecase.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GenreService implements RetrieveGenreByWeatherUseCase {

    private final List<GenreStrategy> genres;

    @Override
    public Genre retrieve(Weather weather) {
        Objects.requireNonNull(weather, "weather must not be null");

        return genres.stream()
                .filter(strategy -> strategy.accepts(weather))
                .findFirst()
                .get()
                .getGenre();
    }

}
