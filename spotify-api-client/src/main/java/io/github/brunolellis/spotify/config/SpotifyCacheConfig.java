package io.github.brunolellis.spotify.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import io.github.brunolellis.spotify.ports.out.SearchResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class SpotifyCacheConfig {

    @Bean
    public Cache<Genre, SearchResponse> spotifyCache() {
        return Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterWrite(Duration.ofSeconds(60))
                .build();
    }

}
