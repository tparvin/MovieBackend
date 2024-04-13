package com.example.MovieProject.controller;

import com.example.MovieProject.dto.MediaItemDto;
import com.example.MovieProject.service.MediaItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MediaItemController {
    private final MediaItemService mediaItemService;

    public MediaItemController(MediaItemService mediaItemService) {
        this.mediaItemService = mediaItemService;
    }

    @PostMapping("/media-item")
    public ResponseEntity<MediaItemDto> addMediaItem(@Valid @RequestBody MediaItemDto mediaItemDto) {
        MediaItemDto createdMediaItem = mediaItemService.addMediaItem(mediaItemDto);
        return ResponseEntity.ok(createdMediaItem);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MediaItemDto>> getAllMovies() {
        List<MediaItemDto> mediaItemDtoList = mediaItemService.retrieveAllMovies();
        return ResponseEntity.ok(mediaItemDtoList);
    }

    @GetMapping("/tv-shows")
    public ResponseEntity<List<MediaItemDto>> getAllTvShows() {
        List<MediaItemDto> mediaItemDtoList = mediaItemService.retrieveAllTvShows();
        return ResponseEntity.ok(mediaItemDtoList);
    }

    @GetMapping("/media-item")
    public ResponseEntity<List<MediaItemDto>> getAllMovies(@RequestParam() String title) {
        List<MediaItemDto> mediaItemDtoList = mediaItemService.findByNameContainingTitle(title);
        return ResponseEntity.ok(mediaItemDtoList);
    }

    @GetMapping("/media-item/featured")
    public ResponseEntity<List<MediaItemDto>> getAllFeaturedMediaItems() {
        List<MediaItemDto> mediaItemDtoList = mediaItemService.findAllFeaturedMedia();
        return ResponseEntity.ok(mediaItemDtoList);
    }

    @GetMapping("/tv-shows/featured")
    public ResponseEntity<List<MediaItemDto>> getAllFeaturedTvShows() {
        List<MediaItemDto> mediaItemDtoList = mediaItemService.findAllFeaturedTvShows();
        return ResponseEntity.ok(mediaItemDtoList);
    }

    @GetMapping("/media-item/{id}")
    public ResponseEntity<MediaItemDto> getMediaItemById(@PathVariable String id) {
        MediaItemDto mediaItemDto = mediaItemService.findById(id);
        return ResponseEntity.ok(mediaItemDto);
    }

    @PutMapping("/media-item/{id}")
    public ResponseEntity<MediaItemDto> updateMediaItem(@PathVariable String id, @Valid @RequestBody MediaItemDto mediaItemDto) {
        MediaItemDto updatedMediaItemDto = mediaItemService.updateMediaItem(id, mediaItemDto);
        return ResponseEntity.ok(updatedMediaItemDto);
    }

    @DeleteMapping("/media-item/{id}")
    public ResponseEntity<String> deleteMediaItem(@PathVariable String id) {
        return ResponseEntity.ok(mediaItemService.deleteMediaItem(id));
    }
}
