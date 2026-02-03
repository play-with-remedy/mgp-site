package com.pwr.mgp.controller;

import com.pwr.mgp.dto.OrganizationDto;
import com.pwr.mgp.entity.Organization;
import com.pwr.mgp.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organizations", produces = "application/json")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<OrganizationDto> getOrganizations() {
        return organizationService.getOrganizations();
    }

    @GetMapping("/{id}")
    public OrganizationDto getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    @PostMapping
    public OrganizationDto addOrganization(@RequestBody Organization organization) {
        return organizationService.addOrganization(organization);
    }

    @PatchMapping("/{id}")
    public OrganizationDto updateOrganizationById(@PathVariable Long id, @RequestBody Organization organization) {
        return organizationService.updateOrganizationById(id, organization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.noContent().build();
    }
}
