package com.bootcamp.dev.devcamp.profile.repository;

import com.bootcamp.dev.devcamp.profile.document.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {

    Mono<Profile> findByEmailId(String emailId);

    Mono<Profile> findByEmailIdAndPassword(String emailId, String password);

}