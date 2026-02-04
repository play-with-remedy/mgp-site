package com.pwr.mgp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate dateOfBirth;
    private String fullName;
}
