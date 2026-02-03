package com.pwr.mgp.controller;

import com.pwr.mgp.dto.TournamentDto;
import com.pwr.mgp.entity.Tournament;
import com.pwr.mgp.record.TournamentFilter;
import com.pwr.mgp.service.TournamentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tournaments", produces = "application/json")
public class TournamentController {

    public final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<TournamentDto> getTournaments(@RequestParam(required = false) Long organizationId) {
        return tournamentService.getTournaments(new TournamentFilter(organizationId));
    }

    @GetMapping("/{id}")
    public TournamentDto getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @PostMapping
    public TournamentDto addTournament(@RequestBody Tournament tournament) {
        return tournamentService.addTournament(tournament);
    }

    @PatchMapping("/{id}")
    public TournamentDto updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }
}
