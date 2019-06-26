package io.github.brunolellis.spotify.ports.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchResponse {
    private Track tracks;
}

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
class Track {
    private List<TrackItem> items;

}

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
class TrackItem {
    private List<Artist> artists;
    private String name;

}

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
class Artist {
    private String name;

}