package io.github.brunolellis.playlist.service;

import io.github.brunolellis.playlist.port.out.MusicPort;
import io.github.brunolellis.playlist.port.out.WeatherByCityPort;
import io.github.brunolellis.playlist.port.out.WeatherByCoordinatesPort;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AbstractUseCaseTestConfig.class)
public abstract class AbstractUseCaseTest {

    @MockBean
    protected WeatherByCityPort weatherByCityPort;

    @MockBean
    protected WeatherByCoordinatesPort weatherByCoordinatesPort;

    @MockBean
    protected MusicPort musicPort;

}

@Configuration
@ComponentScan("io.github.brunolellis.playlist")
class AbstractUseCaseTestConfig {
}