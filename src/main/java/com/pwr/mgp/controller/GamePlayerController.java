package com.pwr.mgp.controller;

import com.pwr.mgp.dto.GamePlayerDto;
import com.pwr.mgp.entity.GamePlayer;
import com.pwr.mgp.service.GamePlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/gameWithPlayers", produces = "application/json")
public class GamePlayerController {

    private final GamePlayerService gamePlayerService;

    public GamePlayerController(GamePlayerService gamePlayerService) {
        this.gamePlayerService = gamePlayerService;
    }

    @GetMapping
    public List<GamePlayerDto> getGameWithPlayers() {
        return gamePlayerService.getGamePlayers();
    }

    @GetMapping("/{id}")
    public List<GamePlayerDto> getGameWithPlayersById(@PathVariable Long id) {
        return gamePlayerService.getGamePlayerByGame(id);
    }

    @PatchMapping("/{id}")
    public GamePlayerDto updateGameWithPlayers(@PathVariable Long id, @RequestBody GamePlayer gamePlayer) {
        return gamePlayerService.updateGameWithPlayersById(id, gamePlayer);
    }

    @PostMapping
    public GamePlayerDto addGamWithPlayers(@RequestBody GamePlayer gamePlayer) {
        return gamePlayerService.addGameWithPlayers(gamePlayer);
    }

}
