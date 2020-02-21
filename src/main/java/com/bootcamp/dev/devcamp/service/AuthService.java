package com.bootcamp.dev.devcamp.service;

import com.bootcamp.dev.devcamp.model.profile.Profile;
import com.bootcamp.dev.devcamp.repository.AuthRepository;
import com.bootcamp.dev.devcamp.response.ClientError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@AllArgsConstructor
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public Mono<Profile> profileExists(String token) {
        return authRepository.findByUuid(token).map(profile -> {
            return profile;
        }).onErrorMap(error -> {
            return ClientError.unauthorized();
        }).switchIfEmpty(Mono.error(ClientError.unauthorized()));
    }

}
