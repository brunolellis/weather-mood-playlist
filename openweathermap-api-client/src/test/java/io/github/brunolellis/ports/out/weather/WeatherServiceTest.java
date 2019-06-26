package io.github.brunolellis.ports.out.weather;

import io.github.brunolellis.playlist.usecase.Weather;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;

public class WeatherServiceTest extends AbstractWeatherTest {

    @MockBean
    protected OpenWeatherMapGateway openWeatherMapGatewayMock;

    @Autowired
    private WeatherService weatherService;

    @Test
    public void testFindWeatherByCity() {
        OpenWeatherResponse expected = createOpenWeatherResponse(23.1f);

        Mockito.when(openWeatherMapGatewayMock.getTemperature("Campinas"))
                .thenReturn(Mono.just(expected));

        Weather weather = weatherService.findWeatherByCity("Campinas").block();

        assertEquals(23.1F, weather.getCurrentTemperature(), 0.1);
    }

    @Test
    public void testFindWeatherByCoordinates() {
        OpenWeatherResponse expected = createOpenWeatherResponse(22.9f);

        Mockito.when(openWeatherMapGatewayMock.getTemperature(-23.5505, -46.6333))
                .thenReturn(Mono.just(expected));

        Weather weather = weatherService.findWeatherByCoordinates(-23.5505, -46.6333).block();

        assertEquals(22.9F, weather.getCurrentTemperature(), 0.1);
    }

    private OpenWeatherResponse createOpenWeatherResponse(float v) {
        return OpenWeatherResponse.builder()
                .temperature(Temperature.builder()
                        .current(v)
                        .build())
                .build();
    }

}