package io.github.brunolellis.playlist.adapter.web;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.Track;
import io.github.brunolellis.playlist.usecase.Track.Artist;

import java.util.List;

public class DefaultPlaylistGenerator {

    protected Playlist generate() {
        return Playlist.builder()
                .tracks(List.of(
                        new Track("Nothing Else Matters", List.of(new Artist("Metallica"))),
                        new Track("Say Something", List.of(new Artist("Justin Timberlake"), new Artist("Chris Stapleton"))),
                        new Track("A Sua Maneira", List.of(new Artist("Capital Inicial"))),
                        new Track("Eine Kleine Nachtmusik", List.of(new Artist("Wolfgang Amadeus Mozart")))
                ))
                .build();
    }

}
