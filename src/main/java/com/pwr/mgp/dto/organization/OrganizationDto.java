package com.pwr.mgp.dto.organization;

import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.enums.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;
    private String name;
    private String description;
    private String address;
    private LocalDate foundationDate;
    private OrganizationType type;
    private PlayerDto director;
}
