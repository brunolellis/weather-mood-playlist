package io.github.brunolellis.playlist.port.out;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String city) {
        super("City " + city + " not found");
    }

}
