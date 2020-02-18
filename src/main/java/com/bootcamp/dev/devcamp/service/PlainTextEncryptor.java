package com.bootcamp.dev.devcamp.service;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class PlainTextEncryptor {

    public static String encrypt(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public static Boolean isPasswordValid(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

}
