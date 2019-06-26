package io.github.brunolellis.ports.out.spotify;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResponse {
    private Track tracks;
}

@Data
@Builder
class Track {
    private List<TrackItem> items;

}

@Data
@Builder
class TrackItem {
    private List<Artist> artists;
    private String name;

}

@Data
@Builder
class Artist {
    private String name;

}