package com.pwr.mgp.service;

import com.pwr.mgp.dto.organization.OrganizationDto;
import com.pwr.mgp.entity.Organization;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDto> getOrganizations();

    OrganizationDto getOrganizationById(Long id);

    OrganizationDto addOrganization(Organization organization);

    OrganizationDto updateOrganizationById(Long id, Organization organization);

    void deleteOrganizationById(Long id);

}
