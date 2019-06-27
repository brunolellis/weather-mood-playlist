package io.github.brunolellis.playlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import java.time.Duration;

@Configuration
public class ResiliencyConfig {

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.of("playlist",
                CircuitBreakerConfig.custom()
                        .failureRateThreshold(50f)
                        .waitDurationInOpenState(Duration.ofSeconds(60))
                        .ringBufferSizeInHalfOpenState(10)
                        .ringBufferSizeInClosedState(100)
                        .build());

        return circuitBreaker;
    }

}
