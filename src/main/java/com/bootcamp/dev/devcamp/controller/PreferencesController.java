package com.bootcamp.dev.devcamp.controller;

import com.bootcamp.dev.devcamp.model.profile.Preferences;
import com.bootcamp.dev.devcamp.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/preferences")
@CrossOrigin
@PreAuthorize("hasRole('USER')")
public class PreferencesController {

    @Autowired
    PreferencesService preferencesService;


    @GetMapping
    public Mono<Preferences> getPreferences() {
        return preferencesService.getPreferences();
    }
}
