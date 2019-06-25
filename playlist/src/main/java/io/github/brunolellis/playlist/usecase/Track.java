package io.github.brunolellis.playlist.usecase;

import lombok.Data;

import java.util.List;

@Data
public class Track {

    private final String name;
    private final List<Artist> artists;


    @Data
    public static class Artist {
        private final String name;
    }
}
