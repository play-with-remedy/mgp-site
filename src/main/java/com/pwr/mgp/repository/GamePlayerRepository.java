package com.pwr.mgp.repository;

import com.pwr.mgp.dto.GamePlayerDto;
import com.pwr.mgp.entity.Game;
import com.pwr.mgp.entity.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {

   List<GamePlayer> findAllByGameId(Long gameId);
}
