package com.gameportal.service;

import com.gameportal.dto.GameDto;
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
                .orElseThrow(()-> new RuntimeException("Game not found with id: " + id));
        return gameMapper.toDto(game);
    }
}
