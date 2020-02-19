package com.bootcamp.dev.devcamp.model.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Language {
    
    @Field("iso_639_1")
    private String iso6391;

    @Field("name")
    private String name;

}
