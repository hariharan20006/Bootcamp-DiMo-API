package com.bootcamp.dev.devcamp.controller;

import com.bootcamp.dev.devcamp.response.SuccessResponse;
import com.bootcamp.dev.devcamp.model.link.ProfileBody;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping(("/api/profile"))
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public Mono<SuccessResponse> createAccount(@RequestBody ProfileBody profileBody) {
        return profileService.createProfile(profileBody);
    }

    @PostMapping("/login")
    public Mono<Token> login(@RequestBody ProfileBody profileBody) {
        return profileService.login(profileBody);
    }
}

