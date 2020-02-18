package com.bootcamp.dev.devcamp.model.profile;

import com.bootcamp.dev.devcamp.repository.ProfileRepository;
import com.bootcamp.dev.devcamp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileConfiguration {

    @Autowired
    public ProfileRepository profileRepository;

    @Bean
    public ProfileService profileService() {
        return new ProfileService();
    }
}
