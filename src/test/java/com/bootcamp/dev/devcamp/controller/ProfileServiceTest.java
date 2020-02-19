package com.bootcamp.dev.devcamp.controller;

import com.bootcamp.dev.devcamp.model.link.CreateProfile;
import com.bootcamp.dev.devcamp.model.profile.Profile;
import com.bootcamp.dev.devcamp.repository.ProfileRepository;
import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.response.SuccessResponse;
import com.bootcamp.dev.devcamp.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    ProfileRepository profileRepository;
    ProfileService profileService;

    @BeforeEach
    public void setUp() {
        profileRepository = mock(ProfileRepository.class);
        profileService = new ProfileService(profileRepository);
    }


    @Test
    public void createProfileWithValidProfileBody() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "Test@123", "Johnn", "Clement");
        Mono<Profile> voidReturn = Mono.empty();
        Mono.just(SuccessResponse.justSuccess());
        when(profileRepository.findByEmailId("johnn@llll.com")).thenReturn(voidReturn);
        when(profileRepository.save(any())).thenReturn(voidReturn);
        profileService.createProfile(createProfileRequest);
        verify(profileRepository, times(1)).save(any());
    }

    @Test
    public void createProfileWithInvalidEmailId() {
        CreateProfile createProfileRequest = new CreateProfile("johnnllll.com", "Test@123", "Johnn", "Clement");
        profileService.createProfile(createProfileRequest);
        verify(profileRepository, times(0)).save(any());
    }

    //TODO:: need to cover login tests
}

