package com.pwr.mgp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayerDto {
    private Long id;
    private PlayerDto player;
    private String role;
    private Integer place;
    private Double gamePoint;
    private Double additionalPoint;
    private Double yellowCard;
    private Double redCard;
    private Double grayCard;
    private Double violetCard;
    private Double lateness;
}
