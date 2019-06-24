package io.github.brunolellis.ports.out;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.openweathermap")
public class OpenWeatherMapProperties {

    private String appid;
    private String url;

}
