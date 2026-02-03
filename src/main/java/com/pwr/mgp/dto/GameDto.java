package com.pwr.mgp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private String result;
    private int gameNumber;
    private String judge;
    private int tableNumber;
}
