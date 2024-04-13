package com.example.MovieProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "media_item")
public class MediaItem {
    @Id
    private String id;

    private String name;
    private Double price;
    private String synopsis;
    private Boolean isMovie;
    private String smallPosterImagePath;
    private String largePosterImagePath;
    private Double rentalPrice;
    private Double purchasePrice;
    private Boolean isFeatured;

    public Boolean getMovie() {
        return isMovie;
    }

    public void setMovie(Boolean movie) {
        isMovie = movie;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }
}
