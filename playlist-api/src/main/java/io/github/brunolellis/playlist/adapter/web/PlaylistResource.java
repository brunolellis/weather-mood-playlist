package io.github.brunolellis.playlist.adapter.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.brunolellis.playlist.usecase.Playlist;
import io.github.brunolellis.playlist.usecase.city.CityQuery;
import io.github.brunolellis.playlist.usecase.city.RetrievePlaylistByCityUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistResource {

    private final RetrievePlaylistByCityUseCase retrievePlaylistByCityUseCase;

    @GetMapping("/city/{city}")
    public Mono<Playlist> getByCity(@PathVariable String city) {
        return retrievePlaylistByCityUseCase.query(new CityQuery(city));
    }

}
