package com.bootcamp.dev.devcamp.model.movies;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Language;

import java.util.List;

@Document
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

    @Field("production_countries")
    private List<Country> productionCountries;

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


    public long getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getHomepage() {
        return homepage;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<Country> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagLine() {
        return tagLine;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

}
