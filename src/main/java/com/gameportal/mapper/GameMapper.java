package com.gameportal.mapper;

import com.gameportal.dto.GameDto;
import com.gameportal.model.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameDto toDto(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description(game.getDescription())
                .genre(game.getGenre())
                .platform(game.getPlatform())
                .releaseYear(game.getReleaseYear())
                .averageRating(game.getAverageRating())
                .imageUrl(game.getImageUrl())
                .build();
    }
}
