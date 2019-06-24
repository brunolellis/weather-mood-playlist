package io.github.brunolellis.playlist.service;

import io.github.brunolellis.playlist.port.out.MusicPort;
import io.github.brunolellis.playlist.port.out.WeatherByCoordinatesPort;
import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.Weather;
import io.github.brunolellis.playlist.usecase.coordinates.CoordinatesQuery;
import io.github.brunolellis.playlist.usecase.coordinates.RetrievePlaylistByCoordinatesUseCase;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import io.github.brunolellis.playlist.usecase.genre.RetrieveGenreByWeatherUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrievePlaylistByCoordinatesService implements RetrievePlaylistByCoordinatesUseCase {

    private final WeatherByCoordinatesPort weatherByCoordinatesPort;
    private final MusicPort musicPort;
    private final RetrieveGenreByWeatherUseCase genreService;

    @Override
    public Playlist query(CoordinatesQuery query) {
        Weather weather = weatherByCoordinatesPort.findWeatherByCoordinates(query.getLatitude(), query.getLongitude());

        Genre genre = genreService.retrieve(weather);

        Playlist playlist = musicPort.searchPlaylistByGenre(genre);

        return playlist;
    }
}
