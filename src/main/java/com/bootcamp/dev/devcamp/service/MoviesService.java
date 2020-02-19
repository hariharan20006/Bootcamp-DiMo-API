package com.bootcamp.dev.devcamp.service;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class MoviesService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Movie> getMovies(Map<String, String> q) {

        Query query = new Query();

        int size = 4;

        for (String key : q.keySet()) {
            if ("pageSize".equals(key)){
                try {
                    size = Integer.parseInt(q.get(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            query.addCriteria(Criteria.where(key).regex(q.get(key), "i"));
        }

        query.limit(size);

        return mongoTemplate.find(query, Movie.class);
    }

    public Movie getMovieByID(Integer movieID) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(movieID));
        Movie movie = mongoTemplate.findOne(query, Movie.class);
        return  movie;

    }
}
