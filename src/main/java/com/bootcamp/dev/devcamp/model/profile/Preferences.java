package com.bootcamp.dev.devcamp.model.profile;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Preferences {

    @Id
    private String id;
    private List<String> genres;
    private List<String> languages;

}
