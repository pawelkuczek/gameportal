package com.gameportal.service;

import com.gameportal.dto.CreateGameRequest;
import com.gameportal.dto.GameDto;
import com.gameportal.exception.GameNotFoundException;
import com.gameportal.mapper.GameMapper;
import com.gameportal.model.Game;
import com.gameportal.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public List<GameDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .toList();
    }

    public GameDto getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(()-> new GameNotFoundException(id));
        return gameMapper.toDto(game);
    }

    public GameDto createGame(CreateGameRequest request) {
        Game game = new Game();
        game.setTitle(request.getTitle());
        game.setDescription(request.getDescription());
        game.setGenre(request.getGenre());
        game.setPlatform(request.getPlatform());
        game.setReleaseYear(request.getReleaseYear());
        game.setImageUrl(request.getImageUrl());

        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }
}
