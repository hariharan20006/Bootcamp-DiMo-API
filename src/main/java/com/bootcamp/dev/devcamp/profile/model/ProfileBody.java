package com.bootcamp.dev.devcamp.profile.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileBody {

    private String emailId;
    private String password;

}
