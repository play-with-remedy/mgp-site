package com.pwr.mgp.repository;

import com.pwr.mgp.entity.Game;
import com.pwr.mgp.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> , JpaSpecificationExecutor<Game> {
}
