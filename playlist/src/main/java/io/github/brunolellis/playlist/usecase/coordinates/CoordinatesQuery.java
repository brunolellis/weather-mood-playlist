package io.github.brunolellis.playlist.usecase.coordinates;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoordinatesQuery {

    private final float latitude;
    private final float longitude;

}
