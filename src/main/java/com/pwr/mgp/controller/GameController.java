package com.pwr.mgp.controller;

import com.pwr.mgp.dto.GameDto;
import com.pwr.mgp.entity.Game;
import com.pwr.mgp.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/games", produces = "application/json")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDto> getGames() {
        return gameService.getGames();
    }

    @PostMapping
    public GameDto addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @PatchMapping("/{id}")
    public GameDto updateGame(@PathVariable Long id, @RequestBody Game game) {
        return gameService.updateGameById(id, game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGameById(id);
        return ResponseEntity.noContent().build();
    }
}
