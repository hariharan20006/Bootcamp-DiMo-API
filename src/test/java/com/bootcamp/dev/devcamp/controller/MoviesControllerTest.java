package com.bootcamp.dev.devcamp.controller;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.service.MoviesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MoviesControllerTest {

    MoviesService moviesService;
    MoviesController moviesController;

    @BeforeEach
    public void setUp() {
        moviesService = mock(MoviesService.class);
        moviesController = new MoviesController(moviesService);
    }

    @Test
    public void testGetMoviesByCriterion() {
        Movie someMovie = new Movie();
        someMovie.setTitle("Some Movie");
        List<Movie> expected = new ArrayList<>();
        expected.add(someMovie);
        HashMap<String,String> parameters = new HashMap<>();
        when(moviesService.getMovies(parameters)).thenReturn(Mono.just(expected));

        Mono<List<Movie>> result = moviesController.getMovies(parameters);

        verify(moviesService, times(1)).getMovies(parameters);
        StepVerifier.create(moviesController.getMovies(parameters)).assertNext(response ->{
            assertEquals(expected.size(),response.size());
        });


    }


    @Test
    public void getMovieByIdTest() {
        Movie expected = new Movie();
        expected.setId("1");
        when(moviesService.getMovieById(1)).thenReturn(Mono.just(expected));

        StepVerifier.create(moviesController.getMovieById(1)).assertNext(response ->
                assertEquals(expected.getId(),response.getId()));
    }
}
