package com.bootcamp.dev.devcamp;

import com.bootcamp.dev.devcamp.model.link.CreateProfile;
import com.bootcamp.dev.devcamp.profile.ProfileValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfileValidatorTest {

    @Test
    public void createProfileWithInvalidEmail() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll", "Test_123", "johnn", "clement");
        assertFalse(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }

    @Test
    public void createProfileWithValidEmail() {
        CreateProfile createProfileRequest = new CreateProfile("test1@tw.com", "P@ssw0rd", "johnn", "clement");
        assertTrue(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }

    @Test
    public void createProfileWithStrongPassword() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "P@ssw0rd", "johnn", "clement");
        assertTrue(ProfileValidator.isCreateProfileValid(createProfileRequest));

    }

    @Test
    public void createProfileWithWeakPassword() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "test123", "johnn", "clement");
        assertFalse(ProfileValidator.isCreateProfileValid(createProfileRequest));

    }

    @Test
    public void createProfileWithLessThanMinimumCharsForPassword() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "test", "johnn", "clement");
        assertFalse(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }

    @Test
    public void createProfileWithNoFirstName() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "test", null, "clement");
        assertFalse(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }

    @Test
    public void createProfileWithNoLasttName() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "test", "Johnn", null);
        assertFalse(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }

    @Test
    public void loginProfileWithWeakPassword() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "test", "Johnn", "Clement");
        assertFalse(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }

    @Test
    public void loginProfileWithStrongPassword() {
        CreateProfile createProfileRequest = new CreateProfile("johnn@llll.com", "Test@123", "Johnn", "Clement");
        assertTrue(ProfileValidator.isCreateProfileValid(createProfileRequest));
    }


}