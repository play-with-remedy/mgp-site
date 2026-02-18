package com.pwr.mgp.service.impl;

import com.pwr.mgp.dto.TeamDto;
import com.pwr.mgp.dto.TournamentDto;
import com.pwr.mgp.entity.Participation;
import com.pwr.mgp.entity.Team;
import com.pwr.mgp.entity.Tournament;
import com.pwr.mgp.mapper.TeamMapper;
import com.pwr.mgp.mapper.TournamentMapper;
import com.pwr.mgp.record.TournamentFilter;
import com.pwr.mgp.repository.PlayerRepository;
import com.pwr.mgp.repository.TeamRepository;
import com.pwr.mgp.repository.TournamentRepository;
import com.pwr.mgp.service.TournamentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    private final TeamMapper teamMapper;
    private final TournamentMapper tournamentMapper;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TeamRepository teamRepository, PlayerRepository playerRepository,
                                 TeamMapper teamMapper, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamMapper = teamMapper;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    public List<TournamentDto> getTournaments(TournamentFilter filter) {
        Sort sort = Sort.by(Sort.Direction.DESC, "startDate");
        Specification<Tournament> spec = (root, query, cb) -> cb.conjunction();

        Long orgId = filter != null ? filter.organizationId() : null;
        if (orgId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("organization").get("id"), orgId));
        }

        return tournamentMapper.toDto(tournamentRepository.findAll(spec, sort));
    }


    @Override
    public TournamentDto getTournamentById(Long id) {
        return tournamentMapper.toDto(tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + id)));
    }

    @Override
    public TournamentDto addTournament(Tournament tournament) {
        return tournamentMapper.toDto(tournamentRepository.save(tournament));
    }

    @Override
    public TournamentDto updateTournament(Long id, @NotNull Tournament tournament) {
        Tournament t = tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + id));

        if (StringUtils.isNotBlank(tournament.getName())) t.setName(tournament.getName());
        if (StringUtils.isNotBlank(tournament.getAddress())) t.setAddress(tournament.getAddress());
        if (tournament.getType() != null) t.setType(tournament.getType());
        if (tournament.getStartDate() != null) t.setStartDate(tournament.getStartDate());
        if (tournament.getEndDate() != null) t.setEndDate(tournament.getEndDate());
        if (tournament.getOrganization() != null) t.setOrganization(tournament.getOrganization());

        return tournamentMapper.toDto(tournamentRepository.save(t));
    }

    public List<TeamDto> getTournamentTeams(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + id));

        return teamMapper.toDto(tournament.getTeams());
    }

    @Transactional
    @Override
    public TournamentDto createTournamentTeam(Long id, Team team) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found: " + id));

        Team savedTeam = teamRepository.save(getNewTeam(team, tournament));

        if (tournament.getTeams() == null) {
            tournament.setTeams(new ArrayList<>());
        }

        tournament.getTeams().add(savedTeam);
        tournamentRepository.save(tournament);

        return tournamentMapper.toDto(tournament);
    }

    private @NotNull Team getNewTeam(@NotNull Team team, Tournament tournament) {
        Team newTeam = new Team(null, tournament, team.getName(), "Registered", null);
        Set<Participation> members = Optional.ofNullable(team.getMembers()).orElse(Set.of()).stream()
                .map(m -> m.getPlayer().getId())
                .filter(Objects::nonNull)
                .distinct()
                .map(playerId -> {
                    Participation p = new Participation();
                    p.setPlayer(playerRepository.getReferenceById(playerId));
                    p.setTournament(tournament);
                    p.setTeam(newTeam);
                    return p;
                })
                .collect(Collectors.toSet());


        newTeam.setMembers(members);
        return newTeam;
    }
}
