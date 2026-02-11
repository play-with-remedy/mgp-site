package com.pwr.mgp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private String result;
    private int gameNumber;
    private String judge;
    private int tableNumber;
    private PlayerDto firstKilled;
    private Double compensationIndex;
    private String bestMove;
    private List<GamePlayerDto> participants;
}
