package com.gameportal.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameDto {
    private Long id;

    private String title;

    private String description;

    private String genre;

    private String platform;

    private Integer releaseYear;

    private Double averageRating;

    private String imageUrl;
}
