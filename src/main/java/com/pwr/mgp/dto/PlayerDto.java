package com.pwr.mgp.dto;

import com.pwr.mgp.dto.organization.UserOrganizationDto;
import com.pwr.mgp.enums.BDayVisibility;
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
    private String quotation;
    private LocalDate startDate;
    private LocalDate dateOfBirth;
    private BDayVisibility birthdateVisibility;
    private UserOrganizationDto organization;
}
