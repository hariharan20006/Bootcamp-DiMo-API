package com.bootcamp.dev.devcamp.profile.document;

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

    @CreatedDate
    private Date createdDate;
    private String uuid;


    public Profile(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
        uuid = UUID.randomUUID().toString();
        createdDate = new Date();
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
