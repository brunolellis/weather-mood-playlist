package io.github.brunolellis.playlist.usecase.coordinates;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesQueryTest {

    @Test
    public void testValidCoordinates() {
        new CoordinatesQuery(0, 0);
    }

    @Test
    public void testMininumValuesLatitudeAndLongitude() {
        new CoordinatesQuery(-90, -180);
    }

    @Test
    public void testMaximumValuesLatitudeAndLongitude() {
        new CoordinatesQuery(90, 180);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinimumLatitude() {
        try {
            new CoordinatesQuery(-90.01, 0);

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid latitude: -90.01", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMaximumLatitude() {
        try {
            new CoordinatesQuery(90.01, 0);

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid latitude: 90.01", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinimumLongitude() {
        try {
            new CoordinatesQuery(0, -180.01);

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid longitude: -180.01", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMaximumLongitude() {
        try {
            new CoordinatesQuery(0, 180.01);

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid longitude: 180.01", e.getMessage());
            throw e;
        }
    }

}