package com.pwr.mgp.repository;

import com.pwr.mgp.entity.Player;
import com.pwr.mgp.entity.Tournament;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long>, JpaSpecificationExecutor<Tournament> {

    List<Tournament> findAllByOrganizationId(Long organizationId, Sort sort);
}
