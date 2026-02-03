package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.GameDto;
import com.pwr.mgp.dto.GamePlayerDto;
import com.pwr.mgp.entity.Game;
import com.pwr.mgp.entity.GamePlayer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PlayerMapper.class } )
public interface GamePlayerMapper {

    GamePlayerDto toDto(GamePlayer game);

    List<GamePlayerDto> toDto(List<GamePlayer> games);
}