package com.pwr.mgp.service;

import com.pwr.mgp.dto.GamePlayerDto;
import com.pwr.mgp.entity.GamePlayer;

import java.util.List;
import java.util.Optional;

public interface GamePlayerService {

    List<GamePlayerDto> getGamePlayers();

    List<GamePlayerDto> getGamePlayerByGame(Long id);

    GamePlayerDto updateGameWithPlayersById(Long id, GamePlayer gamePlayer);

    GamePlayerDto addGameWithPlayers(GamePlayer gamePlayer);
}
