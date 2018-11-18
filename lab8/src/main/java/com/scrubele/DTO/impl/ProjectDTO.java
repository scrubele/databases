package com.scrubele.DTO.impl;

import com.scrubele.DTO.DTO;
import com.scrubele.controller.ArtistController;
import com.scrubele.domain.Artist;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.NoSuchProjectException;
import org.springframework.hateoas.Link;

import java.sql.Date;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProjectDTO extends DTO<Project> {
    public ProjectDTO(Project project, Link link) throws NoSuchProjectException {
        super(project, link);
//        add(linkTo(methodOn(ArtistController.class).getArtistsByProjectId(getEntity().getId())).withRel("artists"));
    }

    public Long getPersonId() {
        return getEntity().getId();
    }

    public String getName() {
        return getEntity().getName();
    }

    public Date getDateEnd() {
        return getEntity().getDateEnd();
    }

    public Date getDateStart() {
        return getEntity().getDateStart();
    }

//    public Set<Artist> getArtists() {
//        return getEntity().getArtists();
//    }
//


}
