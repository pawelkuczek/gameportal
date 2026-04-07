package com.gameportal.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateGameRequest {

    @Size(max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private String genre;

    private String platform;

    @Min(value = 1958, message = "Release year must be 1958 or later")
    @Max(value = 2030, message = "Release year must be 1958 or later")
    private Integer releaseYear;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    private String imageUrl;
}
