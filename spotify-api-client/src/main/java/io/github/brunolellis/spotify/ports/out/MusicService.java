package io.github.brunolellis.spotify.ports.out;

import io.github.brunolellis.playlist.port.out.MusicPort;
import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.Track;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicService implements MusicPort {

    private final SpotifyGateway gateway;

    @Override
    public Mono<Playlist> searchPlaylistByGenre(Genre genre) {
        return gateway.searchTracksByGenre(genre)
                .map(e -> e.getTracks())
                .flatMapMany(e -> Flux.fromIterable(e.getItems()))
                .reduce(new Playlist(), (playlist, trackItem) -> {

                    var artists = convertArtists(trackItem.getArtists());
                    Track track = new Track(trackItem.getName(), artists);

                    playlist.getTracks().add(track);
                    return playlist;
                });
    }

    private List<Track.Artist> convertArtists(List<Artist> artists) {
        return artists.stream()
                .map(artist -> new io.github.brunolellis.playlist.usecase.Track.Artist(artist.getName()))
                .collect(Collectors.toList());
    }
}
