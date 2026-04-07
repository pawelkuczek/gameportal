package com.gameportal.controller;

import com.gameportal.dto.GameDto;
import com.gameportal.dto.UpdateGameRequest;
import com.gameportal.service.GameService;
import lombok.RequiredArgsConstructor;
import com.gameportal.dto.CreateGameRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody @Valid CreateGameRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.createGame(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable Long id, @RequestBody @Valid UpdateGameRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.updateGame(id, request));
    }
}
