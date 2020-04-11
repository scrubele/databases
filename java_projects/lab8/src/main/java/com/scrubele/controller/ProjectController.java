package com.scrubele.controller;

import com.scrubele.DTO.impl.ProjectDTO;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.ExistsArtistsForProjectException;
import com.scrubele.exceptions.NoSuchArtistException;
import com.scrubele.exceptions.NoSuchProjectException;
import com.scrubele.service.ProjectService;
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
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping(value = "/api/project/artist/{artist_id}")
    public ResponseEntity<Set<ProjectDTO>> getProjectsByArtistID(@PathVariable Long artist_id) throws NoSuchArtistException, NoSuchProjectException {
        Set<Project> ProjectSet = projectService.getProjectsByArtistId(artist_id);
        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();

        Set<ProjectDTO> ProjectsDTO = new HashSet<>();
        for (Project entity : ProjectSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProjectDTO dto = new ProjectDTO(entity, selfLink);
            ProjectsDTO.add(dto);
        }

        return new ResponseEntity<>(ProjectsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/project/{project_id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long project_id) throws NoSuchProjectException, NoSuchArtistException {
        Project Project = projectService.getProject(project_id);
        Link link = linkTo(methodOn(ProjectController.class).getProject(project_id)).withSelfRel();

        ProjectDTO projectDTO = new ProjectDTO(Project, link);

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/project")
    public ResponseEntity<Set<ProjectDTO>> getAllProjects() throws NoSuchProjectException, NoSuchArtistException {
        List<Project> ProjectSet = projectService.getAllProjects();
        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();

        Set<ProjectDTO> ProjectsDTO = new HashSet<>();
        for (Project entity : ProjectSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProjectDTO dto = new ProjectDTO(entity, selfLink);
            ProjectsDTO.add(dto);
        }

        return new ResponseEntity<>(ProjectsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/project")
    public ResponseEntity<ProjectDTO> addProject(@RequestBody Project newProject) throws NoSuchProjectException, NoSuchArtistException {
        projectService.createProject(newProject);
        Link link = linkTo(methodOn(ProjectController.class).getProject(newProject.getId())).withSelfRel();

        ProjectDTO projectDTO = new ProjectDTO(newProject, link);

        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/project/{project_id}")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody Project uProject, @PathVariable Long project_id) throws NoSuchProjectException, NoSuchArtistException {
        projectService.updateProject(uProject, project_id);
        Project Project = projectService.getProject(project_id);
        Link link = linkTo(methodOn(ProjectController.class).getProject(project_id)).withSelfRel();

        ProjectDTO projectDTO = new ProjectDTO(Project, link);

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/project/{project_id}")
    public  ResponseEntity deleteProject(@PathVariable Long project_id) throws ExistsArtistsForProjectException, NoSuchProjectException {
        projectService.deleteProject(project_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
