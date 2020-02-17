package com.bootcamp.dev.devcamp.profile;

import com.bootcamp.dev.devcamp.model.ClientError;
import com.bootcamp.dev.devcamp.model.SuccessResponse;
import com.bootcamp.dev.devcamp.profile.model.ProfileBody;
import reactor.core.publisher.Mono;

public class ProfileService {


    public Mono<SuccessResponse> createProfile(ProfileBody profileBody) {
        if (isProfileValid(profileBody)) {
            return Mono.just(SuccessResponse.justSuccess());
        } else {
            return Mono.error(ClientError.invalidBody());
        }
    }

    private boolean isProfileValid(ProfileBody profileBody) {
        if (null == profileBody.getEmailId() || null == profileBody.getPassword()) {
            return false;
        }
        return true;
    }
}
