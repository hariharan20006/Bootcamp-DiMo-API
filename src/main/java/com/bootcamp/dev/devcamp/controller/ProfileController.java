package com.bootcamp.dev.devcamp.controller;


import com.bootcamp.dev.devcamp.model.link.CreateProfile;
import com.bootcamp.dev.devcamp.model.link.ProfileLogin;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.response.SuccessResponse;
import com.bootcamp.dev.devcamp.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping(("/api/profile"))
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public Mono<SuccessResponse> createAccount(@RequestBody CreateProfile profileBody) {
        return profileService.createProfile(profileBody);
    }

    @PostMapping("/login")
    public Mono<Token> login(@RequestBody ProfileLogin profileBody) {
        return profileService.login(profileBody);
    }

}

