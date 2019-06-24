package io.github.brunolellis.playlist.service;

import io.github.brunolellis.playlist.port.out.MusicPort;
import io.github.brunolellis.playlist.port.out.WeatherByCityPort;
import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.city.CityQuery;
import io.github.brunolellis.playlist.usecase.city.RetrievePlaylistByCityUseCase;
import io.github.brunolellis.playlist.usecase.genre.RetrieveGenreByWeatherUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RetrievePlaylistByCityService implements RetrievePlaylistByCityUseCase {

    private final WeatherByCityPort weatherByCityPort;
    private final MusicPort musicPort;
    private final RetrieveGenreByWeatherUseCase genreService;

    @Override
    public Mono<Playlist> query(CityQuery query) {
        return weatherByCityPort.findWeatherByCity(query.getCity())
                .map(weather -> genreService.retrieve(weather))
                .flatMap(genre -> musicPort.searchPlaylistByGenre(genre));
    }

}
