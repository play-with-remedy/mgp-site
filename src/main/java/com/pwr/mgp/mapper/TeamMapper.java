package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.TeamDto;
import com.pwr.mgp.entity.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto toDto(Team player);

    List<TeamDto> toDto(List<Team> players);
}