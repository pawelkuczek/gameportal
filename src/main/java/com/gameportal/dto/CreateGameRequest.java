package com.gameportal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateGameRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotNull(message = "Release year is required")
    @Min(value = 1958, message = "Release year must be 1958 or later")
    @Max(value = 2030, message = "Release year must be 1958 or later")
    private Integer releaseYear;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    private String imageUrl;
}
