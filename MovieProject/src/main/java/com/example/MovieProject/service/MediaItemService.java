package com.example.MovieProject.service;

import com.example.MovieProject.dto.MediaItemDto;

import java.util.List;

public interface MediaItemService {
    MediaItemDto addMediaItem(MediaItemDto mediaItemDto);
    List<MediaItemDto> retrieveAllMovies();
    List<MediaItemDto> retrieveAllTvShows();
    List<MediaItemDto> findByNameContainingTitle(String title);
    List<MediaItemDto> findAllFeaturedMedia();
    List<MediaItemDto> findAllFeaturedTvShows();
    MediaItemDto findById(String id);
    MediaItemDto updateMediaItem(String id, MediaItemDto mediaItemDto);
    String deleteMediaItem(String id);
}
