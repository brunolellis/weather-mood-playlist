package io.github.brunolellis.ports.out.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenWeatherResponse {

    @JsonProperty("main")
    private Temperature temperature;

}

@Data
@Builder
class Temperature {

    @JsonProperty("temp")
    private float current;

    @JsonProperty("temp_min")
    private float min;

    @JsonProperty("temp_max")
    private float max;

}