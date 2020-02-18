package com.bootcamp.dev.devcamp.profile;

import com.bootcamp.dev.devcamp.model.ClientError;
import com.bootcamp.dev.devcamp.model.SuccessResponse;
import com.bootcamp.dev.devcamp.profile.model.ProfileBody;
import com.bootcamp.dev.devcamp.profile.model.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProfileControllerTest {

    private ProfileService service;

    private ProfileRestController profileController;

    private ProfileBody createBody;
    private Token loginToken;

    @BeforeEach
    public void setup() {
        service = Mockito.mock(ProfileService.class);
        profileController = new ProfileRestController(service);
        createBody = new ProfileBody(
                "joel@onlytest.com", "password"
        );
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
        createBody.setEmailId(null);
        Mono<SuccessResponse> expectedError = Mono.error(ClientError.invalidBody());
        Mockito.when(service.createProfile(createBody)).thenReturn(expectedError);
        Mono<SuccessResponse> response = profileController.createAccount(createBody);
        assertEquals(expectedError, response);
    }

    @Test
    public void createAccountWithBlankEmail() {
        createBody.setEmailId("");
        Mono<SuccessResponse> expectedError = Mono.error(ClientError.invalidBody());
        Mockito.when(service.createProfile(createBody)).thenReturn(expectedError);

        Mono<SuccessResponse> response = profileController.createAccount(createBody);

        StepVerifier.create(response).expectErrorMatches(throwable ->
                throwable instanceof ClientError).verify();
    }

    @Test
    public void loginAccountWithMatchingEmailAndPassword() {
        Mockito.when(service.login(createBody)).thenReturn(Mono.just(loginToken));
        StepVerifier.create(profileController.login(createBody))
                .assertNext(token -> {
                    assertEquals("SOME_TOKEN_VALUE", token.getToken());
                })
                .expectComplete()
                .verify();

    }

    @Test
    public void loginAccountWithMatchingEmailAndMismatchedPassword() {
        Mono<Token> expectedError = Mono.error(ClientError.badCredentials());

        Mockito.when(service.login(createBody)).thenReturn(expectedError);

        StepVerifier.create(profileController.login(createBody))
                .consumeErrorWith(error -> {
                    assertEquals(ClientError.badCredentials(), error);
                })
                .verify();

    }


}
