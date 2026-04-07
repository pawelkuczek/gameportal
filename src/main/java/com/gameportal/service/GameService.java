package com.gameportal.service;

import com.gameportal.dto.CreateGameRequest;
import com.gameportal.dto.GameDto;
import com.gameportal.dto.UpdateGameRequest;
import com.gameportal.exception.GameNotFoundException;
import com.gameportal.mapper.GameMapper;
import com.gameportal.model.Game;
import com.gameportal.repository.GameRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Transactional(readOnly = true)
    public List<GameDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public GameDto getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        return gameMapper.toDto(game);
    }

    @Transactional
    public GameDto createGame(CreateGameRequest request) {
        Game game = Game.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .genre(request.getGenre())
                .platform(request.getPlatform())
                .releaseYear(request.getReleaseYear())
                .imageUrl(request.getImageUrl())
                .build();
        return gameMapper.toDto(gameRepository.save(game));
    }

    @Transactional
    public GameDto updateGame(Long id, UpdateGameRequest request) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        if (request.getTitle() != null) {
            game.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            game.setDescription(request.getDescription());
        }

        if (request.getGenre() != null) {
            game.setGenre(request.getGenre());
        }

        if (request.getPlatform() != null) {
            game.setPlatform(request.getPlatform());
        }

        if (request.getReleaseYear() != null) {
            game.setReleaseYear(request.getReleaseYear());
        }

        if (request.getImageUrl() != null) {
            game.setImageUrl(request.getImageUrl());
        }

        return gameMapper.toDto(gameRepository.save(game));
    }

    @Transactional
    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        gameRepository.delete(game);
    }
}
