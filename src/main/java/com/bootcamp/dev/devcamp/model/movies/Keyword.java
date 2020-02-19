package com.bootcamp.dev.devcamp.model.movies;

import org.springframework.data.annotation.Id;

public class Keyword {
    // {\"id\": 1463, \"name\": \"culture clash\"}

    @Id
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
