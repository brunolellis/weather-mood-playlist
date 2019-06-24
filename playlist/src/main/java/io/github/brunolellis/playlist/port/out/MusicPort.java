package io.github.brunolellis.playlist.port.out;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.genre.Genre;

public interface MusicPort {
    Playlist searchPlaylistByGenre(Genre genre);
}
