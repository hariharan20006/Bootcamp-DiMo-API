package com.bootcamp.dev.devcamp.controller;


import com.bootcamp.dev.devcamp.model.link.CreateProfile;
import com.bootcamp.dev.devcamp.model.link.ProfileDetailsResponse;
import com.bootcamp.dev.devcamp.model.link.ProfileLogin;
import com.bootcamp.dev.devcamp.model.link.Token;
import com.bootcamp.dev.devcamp.response.ClientError;
import com.bootcamp.dev.devcamp.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        Mono<Token> expected = Mono.just(loginToken);
        Mockito.when(service.createProfile(createBody)).thenReturn(expected);
        Mono<Token> response = profileController.createAccount(createBody);
        assertEquals(expected, response);
    }

    @Test
    public void createAccountWithEmptyEmail() {
        createBody = new CreateProfile(
                "joel@onlytest.com", "Ja_1ohve", "John", "Doe"
        );
        Mono<Token> expectedError = Mono.error(ClientError.invalidBody());
        Mockito.when(service.createProfile(createBody)).thenReturn(expectedError);
        Mono<Token> response = profileController.createAccount(createBody);
        assertEquals(expectedError, response);
    }

    @Test
    public void createAccountWithBlankEmail() {
        createBody = new CreateProfile(
                "", "", "John", "Doe"
        );
        Mono<Token> expectedError = Mono.error(ClientError.invalidBody());
        Mockito.when(service.createProfile(createBody)).thenReturn(expectedError);

        Mono<Token> response = profileController.createAccount(createBody);

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

    @Test
    public void getProfileWithUnAuthorizedToken() {
        String token = "SOME_RANDOM_TOKEN";

        Mockito.when(service.details(token)).thenReturn(Mono.error(ClientError.badCredentials()));

        StepVerifier.create(profileController.details(token))
                .consumeErrorWith(error -> {
                    assertTrue(error instanceof ClientError);
                })
                .verify();
    }

    @Test
    public void getProfileWithAuthorizedToken() {
        String token = "SOME_RANDOM_TOKEN";

        ProfileDetailsResponse expected = new ProfileDetailsResponse("John", "Doe", "john@gmail.com");

        Mockito.when(service.details(token)).thenReturn(Mono.just(expected));

        StepVerifier.create(profileController.details(token))
                .assertNext(profileDetailsResponse -> {
                    assertEquals(expected.getEmailId(), profileDetailsResponse.getEmailId());
                })
                .verifyComplete();
    }

}
