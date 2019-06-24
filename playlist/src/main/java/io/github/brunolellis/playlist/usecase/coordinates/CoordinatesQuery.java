package io.github.brunolellis.playlist.usecase.coordinates;

import lombok.Getter;

@Getter
public class CoordinatesQuery {

    private final double latitude;
    private final double longitude;

    public CoordinatesQuery(double latitude, double longitude) {
        // latitude must be a number between -90 and 90
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Invalid latitude: " + latitude);
        }

        // longitude must be a number between -180 and 180
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

}
