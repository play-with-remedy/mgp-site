package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.TournamentDto;
import com.pwr.mgp.entity.Tournament;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GameMapper.class } )
public interface TournamentMapper {

    TournamentDto toDto(Tournament tournament);

    List<TournamentDto> toDto(List<Tournament> tournaments);
}