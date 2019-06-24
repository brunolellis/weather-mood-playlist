package io.github.brunolellis.playlist.usecase.city;

import io.github.brunolellis.playlist.usecase.Playlist;

public interface RetrievePlaylistByCityUseCase {

    Playlist query(CityQuery query);

}
