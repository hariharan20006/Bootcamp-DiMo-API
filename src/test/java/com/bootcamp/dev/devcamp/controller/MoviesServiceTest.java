package com.bootcamp.dev.devcamp.controller;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.service.MoviesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MoviesServiceTest {

    MongoTemplate mongoTemplate;
    MoviesService moviesService;

    @BeforeEach
    public void setUp(){
        mongoTemplate = mock(MongoTemplate.class);
        moviesService = new MoviesService(mongoTemplate);
    }

    @Test
    public void getMoviesTest(){

        Movie someMovie = new Movie();
        someMovie.setTitle("Some Movie");
        when(mongoTemplate.find(any(), any())).thenReturn(Collections.singletonList(someMovie));

        HashMap params = new HashMap();
        List<Movie> result = moviesService.getMovies(params);
        verify(mongoTemplate, times(1)).find(any(Query.class), any(Class.class));
        assertEquals(someMovie.getTitle(), result.get(0).getTitle());
    }

    //TODO need to wirte test for get movies by id

}
