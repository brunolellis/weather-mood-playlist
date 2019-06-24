package io.github.brunolellis.ports.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenWeatherResponse {

    @JsonProperty("main")
    private Temperature temperature;

}

@Data
class Temperature {

    @JsonProperty("temp")
    private float current;

    @JsonProperty("temp_min")
    private float min;

    @JsonProperty("temp_max")
    private float max;

}