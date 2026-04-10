package com.gameportal.controller;

import com.gameportal.dto.GameDto;
import com.gameportal.dto.PagedResponse;
import com.gameportal.dto.UpdateGameRequest;
import com.gameportal.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.gameportal.dto.CreateGameRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@Tag(name = "Games", description = "Zarządzanie grami — przeglądanie, dodawanie, edycja i usuwanie")
public class GameController {

    private final GameService gameService;

    @GetMapping
    @Operation(
            summary = "Get all games",
            description = "Returns a list of all games in the database"
    )
    @ApiResponse(responseCode = "200", description = "Showing all games or empty list if there are no games")
    public ResponseEntity<PagedResponse<GameDto>> getAllGames(@RequestParam(required = false) String title,
                                                              @RequestParam(required = false) String genre,
                                                              @RequestParam(required = false) String platform,
                                                              @RequestParam(required = false) Integer releaseYear,
                                                              @ParameterObject @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        return ResponseEntity.ok(gameService.getAllGames(title, genre, platform, releaseYear, pageable));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get game by ID",
            description = "Returns a single game by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Game found"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @PostMapping
    @Operation(
            summary = "Create game",
            description = "Creates new game in database"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Game created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<GameDto> createGame(@RequestBody @Valid CreateGameRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.createGame(request));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update game by ID",
            description = "Updates game with given ID, partial and full update are possible"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Game updated"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<GameDto> updateGame(@PathVariable Long id, @RequestBody @Valid UpdateGameRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.updateGame(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete game by ID",
            description = "Deletes a single game with given ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Game deleted"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
    })
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
