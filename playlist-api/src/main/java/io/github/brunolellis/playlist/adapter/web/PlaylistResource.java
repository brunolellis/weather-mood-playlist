package io.github.brunolellis.playlist.adapter.web;

import io.github.brunolellis.playlist.usecase.coordinates.CoordinatesQuery;
import io.github.brunolellis.playlist.usecase.coordinates.RetrievePlaylistByCoordinatesUseCase;
import org.springframework.web.bind.annotation.*;

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

    private final RetrievePlaylistByCoordinatesUseCase retrievePlaylistByCoordinatesUseCase;

    @GetMapping("/city/{city}")
    public Mono<Playlist> getByCity(@PathVariable("city") String city) {
        return retrievePlaylistByCityUseCase.query(new CityQuery(city));
    }

    @GetMapping("/coordinates")
    public Mono<Playlist> getByCoordinates(@RequestParam("lat") float latitude, @RequestParam("lon") float longitude) {
        return retrievePlaylistByCoordinatesUseCase.query(new CoordinatesQuery(latitude, longitude));
    }

}
