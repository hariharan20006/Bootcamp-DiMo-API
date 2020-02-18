package com.bootcamp.dev.devcamp.model.profile;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class Profile {

    @Id
    private String id;
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;

    @CreatedDate
    private Date createdDate;
    private String uuid;


    public Profile(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
        uuid = UUID.randomUUID().toString();
        createdDate = new Date();
    }

    public Profile(String emailId, String password, String firstName, String lastName) {
        this.emailId = emailId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        uuid = UUID.randomUUID().toString();
        createdDate = new Date();
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
