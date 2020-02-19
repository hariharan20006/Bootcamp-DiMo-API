package com.bootcamp.dev.devcamp.model.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Genre {

    @Field("id")
    private Integer id;

    @Field("name")
    private String name;


}
