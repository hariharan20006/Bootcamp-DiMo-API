package com.bootcamp.dev.devcamp.controller;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.service.MoviesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void getMoviesWithTest() {
        HashMap parameters = new HashMap();
        List<Movie> response = new ArrayList<>();
        Movie someMovie = new Movie();
        someMovie.setTitle("Some Movie");
        response.add(someMovie);
        when(moviesService.getMovies(parameters)).thenReturn(response);

        List<Movie> result = moviesController.getMovies(parameters);

        verify(moviesService, times(1)).getMovies(parameters);
        assertEquals(response.get(0).getTitle(), result.get(0).getTitle());
    }


    @Test
    public void getMovieByIdTest() {
        Movie expected = new Movie();
        expected.setId("1");
        when(moviesService.getMovieByID(1)).thenReturn(expected);

        Movie actual = moviesController.getMovieByID(1);

        verify(moviesService, times(1)).getMovieByID(1);
        assertEquals(expected.getId(), actual.getId());
    }
}
