package com.pwr.mgp.service;

import com.pwr.mgp.dto.TournamentDto;
import com.pwr.mgp.entity.Tournament;
import com.pwr.mgp.record.TournamentFilter;

import java.util.List;

public interface TournamentService {

    List<TournamentDto> getTournaments(TournamentFilter tournamentFilter);

    TournamentDto getTournamentById(Long id);

    TournamentDto addTournament(Tournament tournament);

    TournamentDto updateTournament(Long id, Tournament tournament);

}
