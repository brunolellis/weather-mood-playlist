package io.github.brunolellis.playlist.port.out;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import reactor.core.publisher.Mono;

public interface MusicPort {
    Mono<Playlist> searchPlaylistByGenre(Genre genre);
}
