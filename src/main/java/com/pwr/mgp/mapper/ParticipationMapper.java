package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.ParticipationDto;
import com.pwr.mgp.entity.Participation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    ParticipationDto toDto(Participation player);

    List<ParticipationDto> toDto(List<Participation> players);
}