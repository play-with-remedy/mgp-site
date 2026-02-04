package com.pwr.mgp.service.impl;

import com.pwr.mgp.dto.GameDto;
import com.pwr.mgp.entity.Game;
import com.pwr.mgp.mapper.GameMapper;
import com.pwr.mgp.record.GameFilter;
import com.pwr.mgp.repository.GameRepository;
import com.pwr.mgp.service.GameService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<GameDto> getGames(GameFilter filter) {
        Specification<Game> spec = (root, query, cb) -> cb.conjunction();

        Long tournamentId = filter != null ? filter.tournamentId() : null;
        if (tournamentId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tournament").get("id"), tournamentId));
        }

        return gameMapper.toDto(gameRepository.findAll(spec));
    }

    @Override
    public GameDto getGameById(Long id) {
        return gameMapper.toDto(gameRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game not found: " + id)));
    }

    @Override
    public GameDto addGame(@NotNull Game game) {
        return gameMapper.toDto(gameRepository.save(game));
    }

    @Override
    public GameDto updateGameById(Long id, @NotNull Game game) {
        Game g = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game not found: " + id));

        if (game.getGameNumber() != 0 ) g.setGameNumber(game.getGameNumber());
        if (StringUtils.isNotBlank(game.getJudge())) g.setJudge(game.getJudge());
        if (StringUtils.isNotBlank(game.getResult().toString())) g.setResult(game.getResult());
        if (game.getTableNumber() != 0) g.setTableNumber(game.getTableNumber());
        if (game.getTournament() != null) g.setTournament(game.getTournament());
        if (!CollectionUtils.isEmpty(game.getParticipants())) g.getParticipants().addAll(game.getParticipants());
        
        return gameMapper.toDto(gameRepository.save(g));
    }
    
    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }
}
