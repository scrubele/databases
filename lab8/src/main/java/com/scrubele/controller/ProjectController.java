package com.scrubele.controller;

import com.scrubele.DTO.ProjectDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping(value = "/api/project/Artist/{Artist_id}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByArtistID(@PathVariable Long Artist_id) throws NoSuchArtistException, NoSuchProjectException {
        List<Project> ProjectList = projectService.getProjectsByArtistId(Artist_id);
        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();

        List<ProjectDTO> ProjectsDTO = new ArrayList<>();
        for (Project entity : ProjectList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProjectDTO dto = new ProjectDTO(entity, selfLink);
            ProjectsDTO.add(dto);
        }

        return new ResponseEntity<>(ProjectsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/project/{project_id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long Project_id) throws NoSuchProjectException, NoSuchArtistException {
        Project Project = projectService.getProject(Project_id);
        Link link = linkTo(methodOn(ProjectController.class).getProject(Project_id)).withSelfRel();

        ProjectDTO projectDTO = new ProjectDTO(Project, link);

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/project")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() throws NoSuchProjectException, NoSuchArtistException {
        List<Project> ProjectList = projectService.getAllProjects();
        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();

        List<ProjectDTO> ProjectsDTO = new ArrayList<>();
        for (Project entity : ProjectList) {
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
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody Project uProject, @PathVariable Long Project_id) throws NoSuchProjectException, NoSuchArtistException {
        projectService.updateProject(uProject, Project_id);
        Project Project = projectService.getProject(Project_id);
        Link link = linkTo(methodOn(ProjectController.class).getProject(Project_id)).withSelfRel();

        ProjectDTO projectDTO = new ProjectDTO(Project, link);

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/project/{project_id}")
    public  ResponseEntity deleteProject(@PathVariable Long Project_id) throws ExistsArtistsForProjectException, NoSuchProjectException {
        projectService.deleteProject(Project_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
