package io.github.brunolellis.playlist.port.out;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String city) {
        super("City " + city + " not found");
    }

    public CityNotFoundException(double latitude, double longitude) {
        super("City not found with following geographic coordinates: lat=" + latitude + ", lon=" + longitude);
    }
}
