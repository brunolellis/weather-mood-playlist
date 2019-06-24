package io.github.brunolellis.playlist.service;

import io.github.brunolellis.playlist.port.out.MusicPort;
import io.github.brunolellis.playlist.port.out.WeatherPort;
import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.Weather;
import io.github.brunolellis.playlist.usecase.city.CityQuery;
import io.github.brunolellis.playlist.usecase.city.RetrievePlaylistByCityUseCase;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import io.github.brunolellis.playlist.usecase.genre.RetrieveGenreByWeatherUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrievePlaylistByCityService implements RetrievePlaylistByCityUseCase {

    private final WeatherPort weatherPort;
    private final MusicPort musicPort;
    private final RetrieveGenreByWeatherUseCase genreService;

    @Override
    public Playlist query(CityQuery query) {
        Weather weather = weatherPort.findWeatherByCity(query.getCity());

        Genre genre = genreService.retrieve(weather);

        Playlist playlist = musicPort.searchPlaylistByGenre(genre);

        return playlist;
    }

}
