package com.bootcamp.dev.devcamp.profile;

import com.bootcamp.dev.devcamp.model.SuccessResponse;
import com.bootcamp.dev.devcamp.profile.model.ProfileBody;
import com.bootcamp.dev.devcamp.profile.model.Token;
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
public class ProfileRestController {

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

