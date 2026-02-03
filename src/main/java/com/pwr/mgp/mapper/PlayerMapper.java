package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.entity.Player;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDto toDto(Player player);

    List<PlayerDto> toDto(List<Player> players);

    @AfterMapping
    default void fillFullName(@NotNull Player player, @MappingTarget @NotNull PlayerDto dto) {
        dto.setFullName(buildFullName(player.getFirstName(), player.getNickname(), player.getLastName()));
    }

    private @Nullable String buildFullName(String firstName, String nickname, String lastName) {
        String result = Stream.of(firstName, nickname, lastName)
                .filter(StringUtils::isNotBlank)
                .map(StringUtils::trim)
                .collect(Collectors.joining(" "));

        return StringUtils.isNotBlank(result) ? result : null;
    }
}