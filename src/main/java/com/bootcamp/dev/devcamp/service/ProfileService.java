package com.bootcamp.dev.devcamp.service;


import com.bootcamp.dev.devcamp.model.link.CreateProfile;
import com.bootcamp.dev.devcamp.model.link.ProfileDetailsResponse;
import com.bootcamp.dev.devcamp.model.link.ProfileLogin;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.model.profile.Profile;
import com.bootcamp.dev.devcamp.profile.ProfileValidator;
import com.bootcamp.dev.devcamp.repository.ProfileRepository;
import com.bootcamp.dev.devcamp.response.ClientError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Mono<Token> createProfile(CreateProfile profileBody) {
        if (ProfileValidator.isCreateProfileValid(profileBody)) {
            Profile profile = new Profile(profileBody.getEmailId(), PlainTextEncryptor.encrypt(profileBody.getPassword()), profileBody.getFirstName(), profileBody.getLastName());
            return profileRepository.findByEmailId(profile.getEmailId())
                    .flatMap(existingUser ->
                            Mono.error(ClientError.userAlreadyExists()))
                    .then(profileRepository.save(profile).map(__ -> {
                        return new Token(profile.getUuid());
                    }));
        } else {
            return Mono.error(ClientError.invalidBody());
        }
    }

    public Mono<Token> login(ProfileLogin profileBody) {

        if (ProfileValidator.isLoginCredentialsValid(profileBody)) {
            Profile profile = new Profile(profileBody.getEmailId(), profileBody.getPassword());
            Token response = new Token();
            return profileRepository.findByEmailId(profile.getEmailId())
                    .map(existingUser -> {
                        if (PlainTextEncryptor.isPasswordValid(profileBody.getPassword(), existingUser.getPassword())) {
                            response.setToken(existingUser.getUuid());
                            return response;
                        }
                        throw new RuntimeException();
                    }).onErrorMap(error -> {
                        return ClientError.badCredentials();
                    })
                    .switchIfEmpty(Mono.error(ClientError.badCredentials()));
        } else {
            return Mono.error(ClientError.invalidBody());
        }
    }

    public Mono<ProfileDetailsResponse> details(String authorizationToken) {
        if (null == authorizationToken || authorizationToken.isEmpty()) {
            return Mono.error(ClientError.unauthorized());
        }
        return profileRepository.findByUuid(authorizationToken)
                .map(ProfileDetailsResponse::from)
                .onErrorMap(error -> {
                    return ClientError.unauthorized();
                })
                .switchIfEmpty(Mono.error(ClientError.unauthorized()));
    }
}
