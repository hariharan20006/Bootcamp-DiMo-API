package com.bootcamp.dev.devcamp.service;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.HashMap;


@Service
public class MoviesService {

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    private MoviesRepository moviesRepository;

    public Flux<Movie> getMovies() {
        return moviesRepository.findAll();
//        return moviesRepository.findById(movieID);
    }

    public Movie getMovieByID(Integer movieID) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(movieID));
        Movie movie = mongoTemplate.findOne(query, Movie.class);
        return  movie;

    }
}
