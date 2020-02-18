package com.bootcamp.dev.devcamp.controller;


import com.bootcamp.dev.devcamp.model.CreateProfile;
import com.bootcamp.dev.devcamp.model.link.ProfileLogin;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.profile.ProfileController;
import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.response.SuccessResponse;
import com.bootcamp.dev.devcamp.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProfileControllerTest {

    private ProfileService service;

    private ProfileController profileController;

    private CreateProfile createBody;
    private ProfileLogin loginBody;
    private Token loginToken;

    @BeforeEach
    public void setup() {
        service = Mockito.mock(ProfileService.class);
        profileController = new ProfileController(service);

        loginToken = new Token("SOME_TOKEN_VALUE");
    }

    @Test
    public void createAccount() {
        Mono<SuccessResponse> expected = Mono.just(SuccessResponse.justSuccess());
        Mockito.when(service.createProfile(createBody)).thenReturn(expected);
        Mono<SuccessResponse> response = profileController.createAccount(createBody);
        assertEquals(expected, response);
    }

    @Test
    public void createAccountWithEmptyEmail() {
        createBody = new CreateProfile(
                "joel@onlytest.com", "Ja_1ohve", "John", "Doe"
        );
        Mono<SuccessResponse> expectedError = Mono.error(ClientError.invalidBody());
        Mockito.when(service.createProfile(createBody)).thenReturn(expectedError);
        Mono<SuccessResponse> response = profileController.createAccount(createBody);
        assertEquals(expectedError, response);
    }

    @Test
    public void createAccountWithBlankEmail() {
        createBody = new CreateProfile(
                "", "", "John", "Doe"
        );
        Mono<SuccessResponse> expectedError = Mono.error(ClientError.invalidBody());
        Mockito.when(service.createProfile(createBody)).thenReturn(expectedError);

        Mono<SuccessResponse> response = profileController.createAccount(createBody);

        StepVerifier.create(response).expectErrorMatches(throwable ->
                throwable instanceof ClientError).verify();
    }

    @Test
    public void loginAccountWithMatchingEmailAndPassword() {
        loginBody = new ProfileLogin("joel@onlytest.com", "Ja_1ohve");

        Mockito.when(service.login(loginBody)).thenReturn(Mono.just(loginToken));

        StepVerifier.create(profileController.login(loginBody))
                .assertNext(token -> {
                    assertEquals("SOME_TOKEN_VALUE", token.getToken());
                })
                .expectComplete()
                .verify();

    }

    @Test
    public void loginAccountWithMatchingEmailAndMismatchedPassword() {
        loginBody = new ProfileLogin("joel@onlytest.com", "Ja_");

        Mono<Token> expectedError = Mono.error(ClientError.badCredentials());

        Mockito.when(service.login(loginBody)).thenReturn(expectedError);

        StepVerifier.create(profileController.login(loginBody))
                .consumeErrorWith(error -> {
                    assertEquals(ClientError.badCredentials(), error);
                })
                .verify();

    }


}
