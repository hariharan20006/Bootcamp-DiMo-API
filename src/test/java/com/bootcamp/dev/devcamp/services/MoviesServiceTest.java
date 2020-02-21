package com.bootcamp.dev.devcamp.services;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.service.MoviesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MoviesServiceTest {

    private MongoTemplate mongoTemplate;
    private MoviesService moviesService;

    @BeforeEach
    public void setUp() {
        mongoTemplate = mock(MongoTemplate.class);
        moviesService = new MoviesService(mongoTemplate);
    }

    @Test
    public void getMoviesTest() {

        Movie someMovie = new Movie();
        someMovie.setTitle("Some Movie");
        List<Movie> expected = new ArrayList<>();
        expected.add(someMovie);

        when(mongoTemplate.find(any(), Movie.class)).thenReturn(expected);

        HashMap<String, String> params = new HashMap<>();

        StepVerifier.create(moviesService.getMovies(params)).assertNext(response -> {
            assertEquals(expected.get(0).getTitle(), response.get(0).getTitle());
        }).verifyComplete();

    }

    //TODO need to wirte test for get movies by id

}
