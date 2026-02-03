package com.pwr.mgp.service.impl;

import com.pwr.mgp.dto.OrganizationDto;
import com.pwr.mgp.entity.Organization;
import com.pwr.mgp.mapper.OrganizationMapper;
import com.pwr.mgp.mapper.PlayerMapper;
import com.pwr.mgp.mapper.TournamentMapper;
import com.pwr.mgp.repository.OrganizationRepository;
import com.pwr.mgp.service.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper,
                                   TournamentMapper tournamentMapper, PlayerMapper playerMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }


    @Override
    public List<OrganizationDto> getOrganizations() {
        return organizationMapper.toDto(organizationRepository.findAll());
    }

    @Override
    public OrganizationDto getOrganizationById(Long id) {
        return organizationMapper.toDto(organizationRepository.findById(id).orElseGet(Organization::new));
    }

    @Override
    public OrganizationDto addOrganization(Organization organization) {
        return organizationMapper.toDto(organizationRepository.save(organization));
    }

    @Override
    public OrganizationDto updateOrganizationById(Long id, @NotNull Organization organization) {
        Organization o = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found: " + id));

        if (StringUtils.isNotBlank(organization.getName())) o.setName(organization.getName());
        if (StringUtils.isNotBlank(organization.getDescription())) o.setDescription(organization.getDescription());
        if (StringUtils.isNotBlank(organization.getAddress())) o.setAddress(organization.getAddress());
        if (organization.getFoundationDate() != null) o.setFoundationDate(organization.getFoundationDate());
        if (organization.getType() != null) o.setType(organization.getType());
        if (organization.getDirector() != null) o.setDirector(organization.getDirector());
        if (!CollectionUtils.isEmpty(organization.getPlayers())) o.getPlayers().addAll(organization.getPlayers());
        if (!CollectionUtils.isEmpty(organization.getTournaments())) o.getTournaments().addAll(organization.getTournaments());
        
        return organizationMapper.toDto(organizationRepository.save(o));
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationRepository.deleteById(id);
    }
}
