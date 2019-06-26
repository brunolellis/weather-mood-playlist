package io.github.brunolellis.ports.out.spotify;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.*;

public class MusicServiceTest extends AbstractSpotifyTest {

    @Autowired
    private MusicService musicService;

    @MockBean
    protected SpotifyGateway spotifyGatewayMock;

    @Test
    public void testRockPlaylist() {
        SearchResponse expected = SearchResponse.builder()
                .tracks(Track.builder()
                        .items(List.of(TrackItem.builder()
                                .name("The Sky Is A Neighborhood")
                                .artists(List.of(new Artist("Foo Fighters"), new Artist("Dave Grohl")))
                                .build()))
                        .build())
                .build();

        Mockito.when(spotifyGatewayMock.searchTracksByGenre(Genre.ROCK))
                .thenReturn(Mono.just(expected));

        Playlist rock = musicService.searchPlaylistByGenre(Genre.ROCK).block();

        var tracks = rock.getTracks();
        assertEquals(1, tracks.size());

        var track = tracks.get(0);
        assertEquals("The Sky Is A Neighborhood", track.getName());

        var artists = track.getArtists();
        assertEquals(2, artists.size());
        assertEquals(artists.get(0).getName(), "Foo Fighters");
        assertEquals(artists.get(1).getName(), "Dave Grohl");
    }

}