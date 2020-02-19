package com.bootcamp.dev.devcamp.model.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Document
@Data
public class ProductionCountry {
    // {\"id\": 28, \"name\": \"Action\"}

    @Field("iso_3166_1")
    private String iso31661;

    @Field("name")
    private String name;

}
