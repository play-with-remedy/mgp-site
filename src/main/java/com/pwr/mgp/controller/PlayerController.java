package com.pwr.mgp.controller;

import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.entity.Player;
import com.pwr.mgp.record.PlayerFilter;
import com.pwr.mgp.service.PlayerService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/players", produces = "application/json")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDto> getPlayers(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long tournamentId) {
        return playerService.getPlayers(new PlayerFilter(organizationId, tournamentId));
    }

    @GetMapping("/{id}")
    public PlayerDto getPlayers(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public PlayerDto addPlayer(@RequestBody @NotNull Player player) {
        if (StringUtils.isBlank(player.getPassword())) {
            player.setPassword(player.getNickname());
        }
        return playerService.addPlayer(player);
    }

    @PatchMapping("/{id}")
    public PlayerDto updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return playerService.updatePlayerById(id, player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public PlayerDto login(@RequestBody Player player) {
        return playerService.loginPlayer(player);
    }
}
