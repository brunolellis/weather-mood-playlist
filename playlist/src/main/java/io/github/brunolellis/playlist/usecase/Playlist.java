package io.github.brunolellis.playlist.usecase;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

    private List<Track> tracks;

    public List<Track> getTracks() {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        return tracks;
    }

}
