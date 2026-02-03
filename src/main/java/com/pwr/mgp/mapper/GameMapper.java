package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.GameDto;
import com.pwr.mgp.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GamePlayerMapper.class } )
public interface GameMapper {

    GameDto toDto(Game game);

    List<GameDto> toDto(List<Game> games);
}