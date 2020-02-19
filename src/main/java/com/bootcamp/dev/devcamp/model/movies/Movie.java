package com.bootcamp.dev.devcamp.model.movies;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "movies")
public class Movie {

    @Field("budget")
    private Long budget;

    @Field("genres")
    private List<Genre> genres;



    @Field("id")
    private String id;


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
    private Double popularity;

    @Field("production_companies")
    private List<ProductionCompany> productionCompanies;

    @Field("production_countries")
    private List<ProductionCountry> productionCountries;

    @Field("release_date")
    private String releaseDate;

    @Field("revenue")
    private Long revenue;

    @Field("runtime")
    private Integer runtime;

    @Field("spoken_languages")
    private List<Language> languages;

    @Field("status")
    private String status;

    @Field("tagline")
    private String tagLine;

    @Field("title")
    private String title;

    @Field("vote_average")
    private Double voteAverage;

    @Field("vote_count")
    private Double voteCount;

    public String getPosterUrl() {
        return posterUrl;
    }

    @Field("movie_poster_url")
    private String posterUrl;

}
