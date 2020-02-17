package com.bootcamp.dev.devcamp.profile;

import com.bootcamp.dev.devcamp.profile.model.ProfileBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ProfileControllerTest {

    private ProfileRestController profileController;

    private ProfileBody createBody;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        profileController = new ProfileRestController(new ProfileService());
        createBody = new ProfileBody(
                "rr@thoughtworks.com", "password"
        );
    }


    @Test
    public void createAccount() {
        profileController.createAccount(createBody);
    }

    @Test
    public void createAccountWithEmptyEmailBody() {
        createBody.setEmailId(null);

    }


}
