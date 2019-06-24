package io.github.brunolellis.playlist.usecase.coordinates;

import io.github.brunolellis.playlist.usecase.Playlist;

public interface RetrievePlaylistByCoordinatesUseCase {

    Playlist query(CoordinatesQuery query);

}
