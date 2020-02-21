package com.bootcamp.dev.devcamp.controller;


import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.service.MoviesService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/movies")
@PreAuthorize("hasRole('USER')")
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @GetMapping("")
    public Mono<List<Movie>> getMovies(@RequestParam Map<String, String> reqParam) {
        return moviesService.getMovies(reqParam);
    }

    @GetMapping("/{movieId}")
    public Mono<Movie> getMovieById(@PathVariable Integer movieId ) {
        return moviesService.getMovieById(movieId);
    }


}
