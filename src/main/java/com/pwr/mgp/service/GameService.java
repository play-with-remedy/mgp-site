package com.pwr.mgp.service;

import com.pwr.mgp.dto.GameDto;
import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.entity.Game;

import java.util.List;

public interface GameService {

    List<GameDto> getGames();

    GameDto addGame(Game game);

    GameDto updateGameById(Long id, Game game);

    void deleteGameById(Long id);

}
