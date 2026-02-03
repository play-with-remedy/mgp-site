package com.pwr.mgp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayerDto {

    private PlayerDto player;
    private String role;
    private Integer place;
    private Double gamePoint;
    private Double additionalPoint;
}
