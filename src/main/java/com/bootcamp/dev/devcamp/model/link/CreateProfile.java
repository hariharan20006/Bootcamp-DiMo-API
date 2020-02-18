package com.bootcamp.dev.devcamp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateProfile {

    private String emailId;
    private String password;
    private String firstName;
    private String lastName;

}
