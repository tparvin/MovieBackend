package com.example.MovieProject.repository;

import com.example.MovieProject.model.MediaItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaItemRepository extends MongoRepository<MediaItem, String> {
    List<MediaItem> findByIsMovieTrue();
    List<MediaItem> findByIsMovieFalse();
    List<MediaItem> findByNameContainingIgnoreCase(String title);
    List<MediaItem> findByIsFeaturedTrue();
    List<MediaItem> findByIsFeaturedTrueAndIsMovieFalse();
}
