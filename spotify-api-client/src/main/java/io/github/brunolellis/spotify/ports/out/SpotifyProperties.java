package io.github.brunolellis.spotify.ports.out;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.spotify")
public class SpotifyProperties {

    private String apiUrl;
    private String authorizationUrl;
    private String clientId;
    private String clientSecret;

}
