package com.bootcamp.dev.devcamp.controller;


import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/movies")
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @GetMapping("")
    public List<Movie> getMovies(
            @RequestParam Map<String, String> reqParam
    ) {

        return moviesService.getMovies(reqParam);
    }

    @GetMapping("/{movieID}")
    public Movie getMovieByID(@PathVariable Integer movieID ) {
        return moviesService.getMovieByID(movieID);
    }


}
