package io.github.brunolellis.playlist.service;

import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.Track;
import io.github.brunolellis.playlist.usecase.Weather;
import io.github.brunolellis.playlist.usecase.city.CityQuery;
import io.github.brunolellis.playlist.usecase.city.RetrievePlaylistByCityUseCase;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RetrievePlaylistByCityUseCaseTest extends AbstractUseCaseTest {

    @Autowired
    private RetrievePlaylistByCityUseCase retrievePlaylistByCityUseCase;

    @Test
    public void whenTemperatureIs19ShouldReturnPopPlaylist() {
        setupWeatherMock("Campinas", 19.0f);

        setupMusicMock(Genre.POP, new Playlist(
                List.of(
                        new Track("The Beatles - I Want To Hold Your Hand"),
                        new Track("The Beatles - We Can Work it Out")
                )));

        CityQuery query = new CityQuery("Campinas");
        Playlist playlist = retrievePlaylistByCityUseCase.query(query);

        assertEquals(2, playlist.getTracks().size());
        assertEquals("The Beatles - I Want To Hold Your Hand", playlist.getTracks().get(0).getName());
        assertEquals("The Beatles - We Can Work it Out", playlist.getTracks().get(1).getName());
    }

    private void setupMusicMock(Genre genre, Playlist playlist) {
        Mockito.when(musicPort.searchPlaylistByGenre(genre))
                .thenReturn(playlist);
    }

    private void setupWeatherMock(String city, float temperature) {
        Mockito.when(weatherByCityPort.findWeatherByCity(city))
                .thenReturn(new Weather(temperature));
    }

    @Test(expected = CityNotFoundException.class)
    public void whenCityIsUnknownShouldThrowCityNotFoundException() {
        String duckburg = "Duckburg";

        Mockito.when(weatherByCityPort.findWeatherByCity(duckburg))
                .thenThrow(new CityNotFoundException(duckburg));

        try {
            retrievePlaylistByCityUseCase.query(new CityQuery(duckburg));

        } catch (CityNotFoundException e) {
            assertEquals("City Duckburg not found", e.getMessage());

            throw e;
        }
    }
}