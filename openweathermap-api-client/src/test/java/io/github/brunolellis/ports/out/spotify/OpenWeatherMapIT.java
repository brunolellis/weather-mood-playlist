package io.github.brunolellis.ports.out.spotify;

import static org.junit.Assert.assertTrue;

import io.github.brunolellis.ports.out.weather.WeatherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.brunolellis.playlist.usecase.Weather;
import reactor.core.publisher.Mono;

public class OpenWeatherMapIT extends AbstractWeatherTest {

	@Autowired
    private WeatherService weatherService;

    @Test
    public void shouldReturnCampinasTemperature() {
        Mono<Weather> campinas = weatherService.findWeatherByCity("Campinas");
        Weather weather = campinas.block();

        assertTrue(weather.getCurrentTemperature() > 0);
        System.out.println("campinas = " + weather.getCurrentTemperature());
    }

    @Test
    public void shouldReturnCampinasLatLonTemperature() {
        Mono<Weather> campinas = weatherService.findWeatherByCoordinates(-22.91, -47.06);
        Weather weather = campinas.block();

        assertTrue(weather.getCurrentTemperature() > 0);
        System.out.println("campinas = " + weather.getCurrentTemperature());
    }

}
