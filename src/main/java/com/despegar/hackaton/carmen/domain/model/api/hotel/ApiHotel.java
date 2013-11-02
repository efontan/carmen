package com.despegar.hackaton.carmen.domain.model.api.hotel;

import java.util.List;

import com.despegar.hackaton.carmen.domain.model.api.geo.ApiGeoLocation;

public class ApiHotel {
    private long id;
    private String name;
    private String description;
    private int starRating;
    private ApiGeoLocation geoLocation;
    private List<String> pictures;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStarRating() {
        return this.starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public ApiGeoLocation getGeoLocation() {
        return this.geoLocation;
    }

    public void setGeoLocation(ApiGeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public List<String> getPictures() {
        return this.pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public ApiHotelReviewSummary getReviewSummary() {
        return this.reviewSummary;
    }

    public void setReviewSummary(ApiHotelReviewSummary reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    private ApiHotelReviewSummary reviewSummary;
}
