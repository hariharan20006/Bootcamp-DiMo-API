package com.bootcamp.dev.devcamp.model.profile;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@Data
public class Profile {
    @Id
    private String id;
    private String emailId;
    private String password;

    @CreatedDate
    private Date createdDate;
    private String uuid;

    public Profile(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
        uuid = UUID.randomUUID().toString();
        createdDate = new Date();
    }
}
