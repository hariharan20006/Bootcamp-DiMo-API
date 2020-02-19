package com.bootcamp.dev.devcamp.model.movies;

import org.springframework.data.annotation.Id;

public class ProductionCompany {

    //{\"name\": \"Ingenious Film Partners\", \"id\": 289}

    private String name;

    @Id
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
