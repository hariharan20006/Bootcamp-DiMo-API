package com.bootcamp.dev.devcamp.service;


import com.bootcamp.dev.devcamp.model.CreateProfile;
import com.bootcamp.dev.devcamp.model.link.ProfileLogin;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.model.profile.Profile;
import com.bootcamp.dev.devcamp.profile.ProfileValidator;
import com.bootcamp.dev.devcamp.repository.ProfileRepository;
import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    public Mono<SuccessResponse> createProfile(CreateProfile profileBody) {

        if (ProfileValidator.isCreateProfileValid(profileBody)) {
            Profile profile = new Profile(profileBody.getEmailId(), profileBody.getPassword(), profileBody.getFirstName(), profileBody.getLastName());
            return profileRepository.findByEmailId(profile.getEmailId())
                    .flatMap(existingUser ->
                            Mono.error(ClientError.userAlreadyExists()))
                    .then(profileRepository.save(profile).map(__ -> {
                        return SuccessResponse.justSuccess();
                    }));
        } else {
            return Mono.error(ClientError.invalidBody());
        }
    }

    public Mono<Token> login(ProfileLogin profileBody) {
        if (ProfileValidator.isLoginCredentialsValid(profileBody)) {
            Profile profile = new Profile(profileBody.getEmailId(), profileBody.getPassword());
            Token response = new Token();
            return profileRepository.findByEmailIdAndPassword(profile.getEmailId(), profile.getPassword())
                    .map(existingUser -> {
                        response.setToken(existingUser.getUuid());
                        return response;
                    }).switchIfEmpty(Mono.error(ClientError.badCredentials()));
        } else {
            return Mono.error(ClientError.invalidBody());
        }
    }
}
