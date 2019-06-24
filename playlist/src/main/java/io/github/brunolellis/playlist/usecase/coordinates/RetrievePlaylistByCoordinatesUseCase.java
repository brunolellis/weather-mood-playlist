package io.github.brunolellis.playlist.usecase.coordinates;

import io.github.brunolellis.playlist.usecase.Playlist;
import reactor.core.publisher.Mono;

public interface RetrievePlaylistByCoordinatesUseCase {

    Mono<Playlist> query(CoordinatesQuery query);

}
