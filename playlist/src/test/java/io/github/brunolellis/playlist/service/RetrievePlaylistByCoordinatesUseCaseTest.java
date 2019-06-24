package io.github.brunolellis.playlist.service;

import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.Track;
import io.github.brunolellis.playlist.usecase.Weather;
import io.github.brunolellis.playlist.usecase.city.CityQuery;
import io.github.brunolellis.playlist.usecase.city.RetrievePlaylistByCityUseCase;
import io.github.brunolellis.playlist.usecase.coordinates.CoordinatesQuery;
import io.github.brunolellis.playlist.usecase.coordinates.RetrievePlaylistByCoordinatesUseCase;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RetrievePlaylistByCoordinatesUseCaseTest extends AbstractUseCaseTest {

    @Autowired
    private RetrievePlaylistByCoordinatesUseCase retrievePlaylistByCoordinatesUseCase;

    @Test
    public void whenTemperatureIs14ShouldReturnRockPlaylist() {
        setupWeatherMock( -22.907104, -47.063240, 14.0f);

        setupMusicMock(Genre.ROCK, new Playlist(
                List.of(
                        new Track("Dream Theater - A Change of Seasons"),
                        new Track("Dream Theater - The Dance Of Eternity")
                )));

        CoordinatesQuery query = new CoordinatesQuery(-22.907104, -47.063240);
        Playlist playlist = retrievePlaylistByCoordinatesUseCase.query(query).block();

        assertEquals(2, playlist.getTracks().size());
        assertEquals("Dream Theater - A Change of Seasons", playlist.getTracks().get(0).getName());
        assertEquals("Dream Theater - The Dance Of Eternity", playlist.getTracks().get(1).getName());
    }

    private void setupMusicMock(Genre genre, Playlist playlist) {
        Mockito.when(musicPort.searchPlaylistByGenre(genre))
                .thenReturn(Mono.just(playlist));
    }

    private void setupWeatherMock(double latitude, double longitude, float temperature) {
        Mockito.when(weatherByCoordinatesPort.findWeatherByCoordinates(latitude, longitude))
                .thenReturn(Mono.just(new Weather(temperature)));
    }

}