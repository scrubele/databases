package com.scrubele.DTO.impl;

import com.scrubele.DTO.DTO;
import com.scrubele.controller.ArtistController;
import com.scrubele.controller.ProjectController;
import com.scrubele.domain.Artist;
import com.scrubele.domain.Organization;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.NoSuchArtistException;
import com.scrubele.exceptions.NoSuchOrganizationException;
import com.scrubele.exceptions.NoSuchProjectException;
import org.springframework.hateoas.Link;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class OrganizationDTO extends DTO<Organization> {
    public OrganizationDTO(Organization organization, Link link) throws NoSuchOrganizationException, NoSuchProjectException, NoSuchArtistException {
        super(organization, link);
        add(linkTo(methodOn(ArtistController.class).getArtistsByOrganizationID(getEntity().getId())).withRel("artists"));
    }

    public Long getOrganizationId() {
        return getEntity().getId();
    }

    public String getOrganizationName() {
        return getEntity().getName();
    }

    public Integer getOrganizationPhoneNumber() {
        return getEntity().getPhoneNumber();
    }

    public String getOrganizationAddress() {
        return getEntity().getAddress();
    }

  

    public Set<Artist> getArtists() {
        return getEntity().getArtists();
    }
}
