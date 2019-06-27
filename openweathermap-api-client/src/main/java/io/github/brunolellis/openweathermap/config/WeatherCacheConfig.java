package io.github.brunolellis.openweathermap.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.brunolellis.openweathermap.ports.out.OpenWeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class WeatherCacheConfig {

    @Bean
    public Cache<String, OpenWeatherResponse> weatherCache() {
        return Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterAccess(Duration.ofMinutes(5))
                .build();
    }
}
