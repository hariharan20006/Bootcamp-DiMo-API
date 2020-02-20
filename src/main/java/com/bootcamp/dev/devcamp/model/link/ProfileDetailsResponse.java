package com.bootcamp.dev.devcamp.model.link;

import com.bootcamp.dev.devcamp.model.profile.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetailsResponse {

    private String firstName;
    private String lastName;
    private String emailId;


    public static ProfileDetailsResponse from(Profile profile) {
        ProfileDetailsResponse profileDetails = new ProfileDetailsResponse();
        profileDetails.firstName = profile.getFirstName();
        profileDetails.lastName = profile.getLastName();
        profileDetails.emailId = profile.getEmailId();
        return profileDetails;
    }

}
