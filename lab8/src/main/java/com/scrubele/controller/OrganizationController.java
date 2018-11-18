package com.scrubele.controller;

import com.scrubele.DTO.OrganizationDTO;
import com.scrubele.domain.Organization;
import com.scrubele.exceptions.ExistsArtistsForOrganizationException;
import com.scrubele.exceptions.NoSuchOrganizationException;
import com.scrubele.exceptions.NoSuchArtistException;
import com.scrubele.exceptions.NoSuchProjectException;
import com.scrubele.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping(value = "/api/organization")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganization() throws NoSuchArtistException, NoSuchProjectException, NoSuchOrganizationException {
        List<Organization> organizationList = organizationService.getAllOrganization();
        Link link = linkTo(methodOn(OrganizationController.class).getAllOrganization()).withSelfRel();

        List<OrganizationDTO> organizationDTO = new ArrayList<>();
        for (Organization entity : organizationList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            OrganizationDTO dto = new OrganizationDTO(entity, selfLink);
            organizationDTO.add(dto);
        }

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/organization/{organization_id}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long class_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        Organization Organization = organizationService.getOrganization(class_id);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(class_id)).withSelfRel();

        OrganizationDTO organizationDTO = new OrganizationDTO(Organization, link);

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
    public  ResponseEntity<OrganizationDTO> updateOrganization(@RequestBody Organization uСlazz, @PathVariable Long class_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        organizationService.updateOrganization(uСlazz, class_id);
        Organization Organization = organizationService.getOrganization(class_id);
        Link link = linkTo(methodOn(OrganizationController.class).getOrganization(class_id)).withSelfRel();

        OrganizationDTO organizationDTO = new OrganizationDTO(Organization, link);

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/organization/{organization_id}")
    public  ResponseEntity deleteOrganization(@PathVariable Long class_id) throws NoSuchOrganizationException, ExistsArtistsForOrganizationException {
        organizationService.deleteOrganization(class_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
