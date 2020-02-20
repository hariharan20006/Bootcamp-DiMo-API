package com.bootcamp.dev.devcamp.repository;

import com.bootcamp.dev.devcamp.model.movies.Movie;
import com.bootcamp.dev.devcamp.model.profile.Preferences;
import com.bootcamp.dev.devcamp.model.profile.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

// TODO: remove it, not in use

@Repository
public interface PreferencesRepository extends ReactiveMongoRepository<Preferences, String> {
}
