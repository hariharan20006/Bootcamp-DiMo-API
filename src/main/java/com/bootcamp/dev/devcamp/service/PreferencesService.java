package com.bootcamp.dev.devcamp.service;

import com.bootcamp.dev.devcamp.model.profile.Preferences;
import com.bootcamp.dev.devcamp.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PreferencesService {

    @Autowired
    PreferencesRepository preferencesRepository;

    public Mono<Preferences> getPreferences() {
        return preferencesRepository.findAll().single();
    }

}
