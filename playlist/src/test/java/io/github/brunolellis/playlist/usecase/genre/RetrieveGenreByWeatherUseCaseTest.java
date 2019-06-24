package io.github.brunolellis.playlist.usecase.genre;

import io.github.brunolellis.playlist.usecase.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetrieveGenreByWeatherUseCaseTestConfig.class)
public class RetrieveGenreByWeatherUseCaseTest {

    @Autowired
    private RetrieveGenreByWeatherUseCase genreWeatherService;

    @Test
    public void testClassicalGenre() {
        Weather weather = new Weather(5);
        Genre genre = genreWeatherService.retrieve(weather);
        assertEquals(Genre.CLASSICAL, genre);
    }

    @Test
    public void testRockGenre() {
        Weather weather = new Weather(14);
        Genre genre = genreWeatherService.retrieve(weather);
        assertEquals(Genre.ROCK, genre);
    }

    @Test
    public void testPopGenre() {
        Weather weather = new Weather(20);
        Genre genre = genreWeatherService.retrieve(weather);
        assertEquals(Genre.POP, genre);
    }

    @Test
    public void testPartyGenre() {
        Weather weather = new Weather(31);
        Genre genre = genreWeatherService.retrieve(weather);
        assertEquals(Genre.PARTY, genre);
    }

    @Test
    public void whenMaxWeatherShouldBePartyGenre() {
        Weather weather = new Weather(Integer.MAX_VALUE);
        Genre genre = genreWeatherService.retrieve(weather);
        assertEquals(Genre.PARTY, genre);
    }

    @Test
    public void whenMinWeatherShouldBeClassicalGenre() {
        Weather weather = new Weather(Integer.MIN_VALUE);
        Genre genre = genreWeatherService.retrieve(weather);
        assertEquals(Genre.CLASSICAL, genre);
    }

}