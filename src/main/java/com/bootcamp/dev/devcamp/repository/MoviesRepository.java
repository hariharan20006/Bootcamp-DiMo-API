package com.bootcamp.dev.devcamp.repository;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

// TODO: remove it, not in use

@Repository
public interface MoviesRepository extends ReactiveMongoRepository<Movie, String> {

}
