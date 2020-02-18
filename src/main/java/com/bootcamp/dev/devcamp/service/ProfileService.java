package com.bootcamp.dev.devcamp.service;

import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.response.SuccessResponse;
import com.bootcamp.dev.devcamp.model.profile.Profile;
import com.bootcamp.dev.devcamp.model.link.ProfileBody;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Mono<SuccessResponse> createProfile(ProfileBody profileBody) {

        if (isProfileDetailsValid(profileBody)) {
            Profile profile = new Profile(profileBody.getEmailId(), profileBody.getPassword());
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

    public Mono<Token> login(ProfileBody profileBody) {
        if (isProfileDetailsValid(profileBody)) {
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

    private boolean isProfileDetailsValid(ProfileBody profileBody) {
        if (null == profileBody.getEmailId() || null == profileBody.getPassword()) {
            return false;
        }
        return true;
    }
}