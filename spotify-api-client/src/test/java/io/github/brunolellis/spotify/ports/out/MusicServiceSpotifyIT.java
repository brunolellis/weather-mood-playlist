package io.github.brunolellis.spotify.ports.out;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.genre.Genre;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class MusicServiceSpotifyIT extends AbstractSpotifyTest {

    @Autowired
    private MusicService musicService;

    @Test
    public void shouldAuthenticate() {
        Playlist playlist = musicService.searchPlaylistByGenre(Genre.ROCK).block();

        assertEquals(playlist.getTracks().size(), 20);

        System.out.println("ROCK = " + playlist);
    }

}
