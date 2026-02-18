package com.pwr.mgp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private String name;
    private String status;
    private Set<ParticipationDto> members;
}
