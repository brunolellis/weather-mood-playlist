package io.github.brunolellis.playlist.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Playlist {

    private List<Track> tracks;

}
