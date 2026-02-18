package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.entity.Player;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDto toDto(Player player);

    List<PlayerDto> toDto(List<Player> players);
}