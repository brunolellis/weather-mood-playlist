package io.github.brunolellis.ports.out.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authorization {

    @JsonProperty("access_token")
    private String accessToken;

}
