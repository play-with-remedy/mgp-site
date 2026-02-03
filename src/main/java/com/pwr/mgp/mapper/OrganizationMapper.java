package com.pwr.mgp.mapper;

import com.pwr.mgp.dto.OrganizationDto;
import com.pwr.mgp.entity.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PlayerMapper.class })
public interface OrganizationMapper {

    OrganizationDto toDto(Organization organization);

    List<OrganizationDto> toDto(List<Organization> organizations);
}