package com.bootcamp.dev.devcamp.repository;

import com.bootcamp.dev.devcamp.model.profile.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AuthRepository extends ReactiveMongoRepository<Profile, String> {

    Mono<Profile> findByUuid(String token);

}
