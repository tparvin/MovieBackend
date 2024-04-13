package com.example.MovieProject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MediaItemDto {
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Synopsis is required")
    @Size(max = 1000, message = "Synopsis can have at most 1000 characters")
    private String synopsis;

    private Boolean isMovie;
    private String smallPosterImagePath;
    private String largePosterImagePath;

    @NotNull(message = "Rental price is required")
    @Positive(message = "Rental price must be positive")
    private Double rentalPrice;

    @NotNull(message = "Purchase price is required")
    @Positive(message = "Purchase price must be positive")
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
