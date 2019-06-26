package io.github.brunolellis.spotify.ports.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authorization {

    @JsonProperty("access_token")
    private String accessToken;

}
