package io.github.brunolellis.ports.out.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherResponse {

    @JsonProperty("main")
    private Temperature temperature;

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Temperature {

    @JsonProperty("temp")
    private float current;

    @JsonProperty("temp_min")
    private float min;

    @JsonProperty("temp_max")
    private float max;

}