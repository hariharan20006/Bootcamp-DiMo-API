package com.bootcamp.dev.devcamp.model.link;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileBody {
    private String emailId;
    private String password;
}
