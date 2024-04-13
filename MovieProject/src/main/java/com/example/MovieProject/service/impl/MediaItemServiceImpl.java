package com.example.MovieProject.service.impl;

import com.example.MovieProject.dto.MediaItemDto;
import com.example.MovieProject.exception.RecordNotFoundException;
import com.example.MovieProject.model.MediaItem;
import com.example.MovieProject.repository.MediaItemRepository;
import com.example.MovieProject.service.MediaItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaItemServiceImpl implements MediaItemService {

    private final MediaItemRepository mediaItemRepository;

    public MediaItemServiceImpl(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

    @Override
    public MediaItemDto addMediaItem(MediaItemDto mediaItemDto) {
        MediaItem mediaItem = toEntity(mediaItemDto);
        return toDto(mediaItemRepository.save(mediaItem));
    }

    @Override
    public List<MediaItemDto> retrieveAllMovies() {
        List<MediaItem> mediaItems = mediaItemRepository.findByIsMovieTrue();
        List<MediaItemDto> mediaItemDtoList = new ArrayList<>();

        for(MediaItem mediaItem : mediaItems){
            MediaItemDto mediaItemDto = toDto(mediaItem);
            mediaItemDtoList.add(mediaItemDto);
        }
        return mediaItemDtoList;
    }

    @Override
    public List<MediaItemDto> retrieveAllTvShows() {
        List<MediaItem> mediaItems = mediaItemRepository.findByIsMovieFalse();
        List<MediaItemDto> mediaItemDtoList = new ArrayList<>();

        for(MediaItem mediaItem : mediaItems){
            MediaItemDto mediaItemDto = toDto(mediaItem);
            mediaItemDtoList.add(mediaItemDto);
        }
        return mediaItemDtoList;
    }

    @Override
    public List<MediaItemDto> findByNameContainingTitle(String title) {
        List<MediaItem> mediaItems = mediaItemRepository.findByNameContainingIgnoreCase(title);
        List<MediaItemDto> mediaItemDtoList = new ArrayList<>();

        for(MediaItem mediaItem : mediaItems){
            MediaItemDto mediaItemDto = toDto(mediaItem);
            mediaItemDtoList.add(mediaItemDto);
        }
        return mediaItemDtoList;
    }

    @Override
    public List<MediaItemDto> findAllFeaturedMedia() {
        List<MediaItem> mediaItems = mediaItemRepository.findByIsFeaturedTrue();
        List<MediaItemDto> mediaItemDtoList = new ArrayList<>();

        for(MediaItem mediaItem : mediaItems){
            MediaItemDto mediaItemDto = toDto(mediaItem);
            mediaItemDtoList.add(mediaItemDto);
        }
        return mediaItemDtoList;
    }

    @Override
    public List<MediaItemDto> findAllFeaturedTvShows() {
        List<MediaItem> mediaItems = mediaItemRepository.findByIsFeaturedTrueAndIsMovieFalse();
        List<MediaItemDto> mediaItemDtoList = new ArrayList<>();

        for(MediaItem mediaItem : mediaItems){
            MediaItemDto mediaItemDto = toDto(mediaItem);
            mediaItemDtoList.add(mediaItemDto);
        }
        return mediaItemDtoList;
    }

    @Override
    public MediaItemDto findById(String id) {
        MediaItem mediaItem = mediaItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("MediaItem Not found at id => %s", id)));
        return toDto(mediaItem);
    }

    @Override
    public MediaItemDto updateMediaItem(String id, MediaItemDto mediaItemDto) {
        MediaItem mediaItem = mediaItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("MediaItem Not found at id => %s", id)));

        mediaItem.setName(mediaItemDto.getName());
        mediaItem.setPrice(mediaItemDto.getPrice());
        mediaItem.setSynopsis(mediaItemDto.getSynopsis());
        mediaItem.setMovie(mediaItemDto.getIsMovie());
        mediaItem.setSmallPosterImagePath(mediaItemDto.getSmallPosterImagePath());
        mediaItem.setLargePosterImagePath(mediaItemDto.getLargePosterImagePath());
        mediaItem.setRentalPrice(mediaItemDto.getRentalPrice());
        mediaItem.setPurchasePrice(mediaItemDto.getPurchasePrice());
        mediaItem.setFeatured(mediaItemDto.getIsFeatured());

        MediaItem updatedMediaItem = mediaItemRepository.save(mediaItem);
        return toDto(updatedMediaItem);
    }

    @Override
    public String deleteMediaItem(String id) {
        MediaItem mediaItem = mediaItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("MediaItem Not found at id => %s", id)));
        mediaItemRepository.delete(mediaItem);
        return "MediaItem with ID " + id + " has been deleted successfully";
    }

    public MediaItemDto toDto(MediaItem mediaItem) {
        return MediaItemDto.builder()
                .id(mediaItem.getId())
                .name(mediaItem.getName())
                .price(mediaItem.getPrice())
                .synopsis(mediaItem.getSynopsis())
                .isMovie(mediaItem.getIsMovie())
                .smallPosterImagePath(mediaItem.getSmallPosterImagePath())
                .largePosterImagePath(mediaItem.getLargePosterImagePath())
                .rentalPrice(mediaItem.getRentalPrice())
                .purchasePrice(mediaItem.getPurchasePrice())
                .isFeatured(mediaItem.getIsFeatured())
                .build();
    }

    public MediaItem toEntity(MediaItemDto mediaItemDto) {
        return MediaItem.builder()
                .id(mediaItemDto.getId())
                .name(mediaItemDto.getName())
                .price(mediaItemDto.getPrice())
                .synopsis(mediaItemDto.getSynopsis())
                .isMovie(mediaItemDto.getIsMovie())
                .smallPosterImagePath(mediaItemDto.getSmallPosterImagePath())
                .largePosterImagePath(mediaItemDto.getLargePosterImagePath())
                .rentalPrice(mediaItemDto.getRentalPrice())
                .purchasePrice(mediaItemDto.getPurchasePrice())
                .isFeatured(mediaItemDto.getIsFeatured())
                .build();
    }
}
