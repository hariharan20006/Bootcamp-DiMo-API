package com.bootcamp.dev.devcamp.profile;

import com.bootcamp.dev.devcamp.profile.repository.ProfileRepository;
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
