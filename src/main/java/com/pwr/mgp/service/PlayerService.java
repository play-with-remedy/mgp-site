package com.pwr.mgp.service;

import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.entity.Player;
import com.pwr.mgp.record.PlayerFilter;

import java.util.List;

public interface PlayerService {

    List<PlayerDto> getPlayers(PlayerFilter playerFilter);

    PlayerDto getPlayerById(Long id);

    PlayerDto addPlayer(Player player);

    PlayerDto updatePlayerById(Long id, Player player);

    void deletePlayerById (Long id);

    PlayerDto loginPlayer(Player player);

}
