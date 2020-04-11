package com.scrubele.controller;

import com.scrubele.DTO.impl.OrganizationDTO;
import com.scrubele.domain.Organization;

import com.scrubele.exceptions.*;
import com.scrubele.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping(value = "/api/organization")
    public ResponseEntity<Set<OrganizationDTO>> getAllOrganization() throws NoSuchArtistException, NoSuchProjectException, NoSuchOrganizationException {
        List<Organization> organizationSet = organizationService.getAllOrganization();
        Link link = linkTo(methodOn(OrganizationController.class).getAllOrganization()).withSelfRel();

        Set<OrganizationDTO> organizationDTO = new HashSet<>();
        for (Organization entity : organizationSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            OrganizationDTO dto = new OrganizationDTO(entity, selfLink);
            organizationDTO.add(dto);
        }

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/organization/{organization_id}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long organization_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        Organization organization = organizationService.getOrganization(organization_id);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(organization_id)).withSelfRel();
        System.out.println(organization);
        OrganizationDTO organizationDTO = new OrganizationDTO(organization, link);

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity<OrganizationDTO> addOrganization(@RequestBody Organization newOrganization) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        organizationService.createOrganization(newOrganization);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(newOrganization.getId())).withSelfRel();

        OrganizationDTO organizationDTO = new OrganizationDTO(newOrganization, link);

        return new ResponseEntity<>(organizationDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity<OrganizationDTO> updateOrganization(@RequestBody Organization uOrganization, @PathVariable Long organization_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        organizationService.updateOrganization(uOrganization, organization_id);
        Organization Organization = organizationService.getOrganization(organization_id);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(organization_id)).withSelfRel();

        OrganizationDTO organizationDTO = new OrganizationDTO(Organization, link);

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity deleteOrganization(@PathVariable Long organization_id) throws NoSuchOrganizationException, ExistsPersonsForCityException, ExistsArtistsForOrganizationException {
        organizationService.deleteOrganization(organization_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
