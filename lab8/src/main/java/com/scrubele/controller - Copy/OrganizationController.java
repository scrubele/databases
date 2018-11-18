package com.scrubele.controller;

import com.scrubele.DTO.DTOBuilder;
import com.scrubele.DTO.impl.OrganizationDTO;
import com.scrubele.domain.Organization;
import com.scrubele.exceptions.ExistsPersonsForCityException;
import com.scrubele.exceptions.NoSuchOrganizationException;
import com.scrubele.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping(value = "/api/organization")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganization() {
        List<Organization> organizationList = organizationService.getAllOrganization();
        System.out.println(organizationList);
        Link link = linkTo(methodOn(OrganizationController.class).getAllOrganization()).withSelfRel();
        List<OrganizationDTO> cities = DTOBuilder.buildDtoListForCollection(organizationList, OrganizationDTO.class, link);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping(value = "/api/organization/{organization_id}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long organization_id) throws NoSuchOrganizationException {
        Organization organization = organizationService.getOrganization(organization_id);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(organization_id)).withSelfRel();
        OrganizationDTO projectDTO = DTOBuilder.buildDtoForEntity(organization, OrganizationDTO.class, link);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity<OrganizationDTO> addOrganization(@RequestBody Organization newOrganization) throws NoSuchOrganizationException {
        organizationService.createOrganization(newOrganization);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(newOrganization.getId())).withSelfRel();
        OrganizationDTO organizationDTO = DTOBuilder.buildDtoForEntity(newOrganization, OrganizationDTO.class, link);
        return new ResponseEntity<>(organizationDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity<OrganizationDTO> updateOrganization(@RequestBody Organization uOrganization, @PathVariable Long organization_id) throws NoSuchOrganizationException {
        organizationService.updateOrganization(uOrganization, organization_id);
        Organization organization = organizationService.getOrganization(organization_id);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(organization_id)).withSelfRel();
        OrganizationDTO organizationDTO = DTOBuilder.buildDtoForEntity(organization, OrganizationDTO.class, link);
        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity deleteOrganization(@PathVariable Long organization_id) throws NoSuchOrganizationException, ExistsPersonsForCityException {
        organizationService.deleteOrganization(organization_id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
