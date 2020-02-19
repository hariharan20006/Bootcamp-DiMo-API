package com.bootcamp.dev.devcamp.repository;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface MoviesRepository extends ReactiveMongoRepository<Movie, String> {
    Mono<Movie> findByTitle(String title);
//    Mono<Movie> findById(Integer id);
}
