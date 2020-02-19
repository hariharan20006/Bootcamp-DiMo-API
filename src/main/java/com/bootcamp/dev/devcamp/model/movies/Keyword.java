package com.bootcamp.dev.devcamp.model.movies;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
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
