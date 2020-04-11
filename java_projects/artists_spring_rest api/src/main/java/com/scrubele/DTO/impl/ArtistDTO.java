package com.scrubele.DTO.impl;

import com.scrubele.DTO.DTO;
import com.scrubele.controller.ArtistController;
import com.scrubele.controller.OrganizationController;
import com.scrubele.controller.ProjectController;
import com.scrubele.domain.Artist;
import com.scrubele.domain.Organization;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.NoSuchArtistException;
import com.scrubele.exceptions.NoSuchOrganizationException;
import com.scrubele.exceptions.NoSuchProjectException;
import org.springframework.hateoas.Link;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ArtistDTO extends DTO<Artist> {
    public ArtistDTO(Artist artist, Link link) throws NoSuchProjectException, NoSuchOrganizationException, NoSuchArtistException {
        super(artist, link);
//        add(linkTo(methodOn(ProjectController.class).getProjectByArtistId(getEntity().getId())).withRel("projects"));
    }

    public Long getArtistId() {
        return getEntity().getId();
    }

    public String getArtistName() {
        return getEntity().getName();
    }

    public String getArtistSurname() {
        return getEntity().getSurname();
    }

    public String getArtistPosition() {
        return getEntity().getPosition();
    }

//    public Set<Project> getArtistProjects() {
//        return getEntity().getProjects();
//    }

    public Organization getOrganizations() {
        return getEntity().getOrganizationsByOrganizations();
    }


}
