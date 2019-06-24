package io.github.brunolellis.playlist.usecase.city;

import io.github.brunolellis.playlist.usecase.Playlist;
import reactor.core.publisher.Mono;

public interface RetrievePlaylistByCityUseCase {

    Mono<Playlist> query(CityQuery query);

}
