package io.github.brunolellis.ports.out;

import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import io.github.brunolellis.playlist.port.out.WeatherByCityPort;
import io.github.brunolellis.playlist.port.out.WeatherByCoordinatesPort;
import io.github.brunolellis.playlist.usecase.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherService implements WeatherByCityPort, WeatherByCoordinatesPort {

    private final OpenWeatherMapGateway gateway;

    @Override
    public Mono<Weather> findWeatherByCity(String city) throws CityNotFoundException {
        return gateway.getTemperature(city);
    }

    @Override
    public Mono<Weather> findWeatherByCoordinates(double latitude, double longitude) {
        return gateway.getTemperature(latitude, longitude);
    }
}
