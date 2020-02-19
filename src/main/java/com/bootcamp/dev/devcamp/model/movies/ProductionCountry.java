package com.bootcamp.dev.devcamp.model.movies;

import org.springframework.data.annotation.Id;

public class ProductionCountry {
    // {\"id\": 28, \"name\": \"Action\"}

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
