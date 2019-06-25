package io.github.brunolellis.ports.out.spotify;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {
    private Track tracks;
}

@Data
class Track {
    private List<TrackItem> items;

}

@Data
class TrackItem {
    private List<Artist> artists;
    private String name;

}

@Data
class Artist {
    private String name;

}