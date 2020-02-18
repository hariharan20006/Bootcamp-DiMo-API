package com.bootcamp.dev.devcamp.profile;

import com.bootcamp.dev.devcamp.model.link.CreateProfile;
import com.bootcamp.dev.devcamp.model.link.ProfileLogin;

public class ProfileValidator {

    private static String email_regex = "^[_A-Za-z0-9-\\\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,15}$";

    public static boolean isCreateProfileValid(CreateProfile profile) {
        if (null == profile.getEmailId() || !profile.getEmailId().matches(email_regex)) {
            return false;
        }
        if (null == profile.getPassword() || !profile.getPassword().matches(password_regex)) {
            return false;
        }
        return null != profile.getFirstName() && null != profile.getLastName();
    }

    public static boolean isLoginCredentialsValid(ProfileLogin login) {
        if (null != login.getEmailId() && !login.getEmailId().matches("^$")) {
            return null != login.getPassword() && login.getPassword().matches(password_regex);
        }
        return false;
    }

}
