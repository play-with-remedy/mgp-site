package com.pwr.mgp.repository;

import com.pwr.mgp.entity.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {

    Optional<Player> findByNicknameIgnoreCase(String nickname);

    List<Player> findAllByOrganizationId(Long organizationId, Sort sort);
}
