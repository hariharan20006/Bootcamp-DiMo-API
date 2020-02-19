package com.bootcamp.dev.devcamp.model.movies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Language;

import java.security.PrivateKey;
import java.util.List;

public class Movie {

    @Field("budget")
    private long budget;

    @Field("genres")
    private List<Genre> genres;

    @Id
    @Field("id")
    private int id;

    @Field("homepage")
    private String homepage;

    @Field("keywords")
    private List<Keyword> keywords;

    @Field("original_language")
    private String originalLanguage;

    @Field("original_title")
    private String originalTitle;

    @Field("overview")
    private String overview;

    @Field("popularity")
    private double popularity;

    @Field("production_companies")
    private List<ProductionCompany> productionCompanies;

    @Field("release_date")
    private String releaseDate;

    @Field("revenue")
    private long revenue;

    @Field("runtime")
    private int runtime;

    @Field("spoken_languages")
    private List<Language> languages;

    @Field("status")
    private String status;

    @Field("tagLine")
    private String tagLine;

    @Field("title")
    private String title;

    @Field("vote_average")
    private double voteAverage;

    @Field("vote_count")
    private double voteCount;

}
