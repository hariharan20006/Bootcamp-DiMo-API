package com.bootcamp.dev.devcamp.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileConfiguration {

    @Bean
    public ProfileService profileService() {
        return new ProfileService();
    }
}
