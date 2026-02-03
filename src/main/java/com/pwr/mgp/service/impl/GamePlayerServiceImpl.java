package com.pwr.mgp.service.impl;

import com.pwr.mgp.dto.GamePlayerDto;
import com.pwr.mgp.entity.GamePlayer;
import com.pwr.mgp.mapper.GamePlayerMapper;
import com.pwr.mgp.repository.GamePlayerRepository;
import com.pwr.mgp.service.GamePlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamePlayerServiceImpl implements GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;
    private final GamePlayerMapper gamePlayerMapper;

    public GamePlayerServiceImpl(GamePlayerRepository gamePlayerRepository, GamePlayerMapper gamePlayerMapper) {
        this.gamePlayerRepository = gamePlayerRepository;
        this.gamePlayerMapper = gamePlayerMapper;
    }

    @Override
    public List<GamePlayerDto> getGamePlayers() {
        return gamePlayerMapper.toDto(gamePlayerRepository.findAll());
    }

    @Override
    public List<GamePlayerDto> getGamePlayerByGame(Long id) {
        return gamePlayerMapper.toDto(gamePlayerRepository.findAllByGameId(id));
    }

    @Override
    public GamePlayerDto addGameWithPlayers(GamePlayer gamePlayer) {
        return gamePlayerMapper.toDto(gamePlayerRepository.save(gamePlayer));
    }

    @Override
    public GamePlayerDto updateGameWithPlayersById(Long id, @NotNull GamePlayer gamePlayer) {
        GamePlayer gp = gamePlayerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game with Players not found: " + id));
        
        if (gamePlayer.getGame() != null ) gp.setGame(gamePlayer.getGame());
        if (gamePlayer.getPlayer() != null) gp.setPlayer(gamePlayer.getPlayer());
        if (StringUtils.isNotBlank(gamePlayer.getRole().toString())) gp.setRole(gamePlayer.getRole());
        if (gamePlayer.getPlace() != 0) gp.setPlace(gamePlayer.getPlace());
        if (gamePlayer.getGamePoint() != null) gp.setGamePoint(gamePlayer.getGamePoint());
        if (gamePlayer.getAdditionalPoint() != null) gp.setAdditionalPoint(gamePlayer.getAdditionalPoint());

        return gamePlayerMapper.toDto(gamePlayerRepository.save(gp));
    }
}
