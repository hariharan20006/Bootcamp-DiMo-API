package com.bootcamp.dev.devcamp.model.movies;

import org.springframework.data.mongodb.core.mapping.Field;

public class Country {
    @Field("iso_639_1")
    private String iso6391;

    @Field("name")
    private String name;

    public String getIso_639_1() {
        return iso6391;
    }

    public String getName() {
        return name;
    }
}
