package com.pwr.mgp.mapper.organization;

import com.pwr.mgp.dto.organization.OrganizationDto;
import com.pwr.mgp.entity.Organization;
import com.pwr.mgp.mapper.PlayerMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PlayerMapper.class })
public interface OrganizationMapper {

    OrganizationDto toDto(Organization organization);

    List<OrganizationDto> toDto(List<Organization> organizations);
}