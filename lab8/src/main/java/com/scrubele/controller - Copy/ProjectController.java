package com.scrubele.controller;

import com.scrubele.DTO.DTOBuilder;
import com.scrubele.DTO.impl.ProjectDTO;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.*;
import com.scrubele.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

//    @GetMapping(value = "/api/project/artist/{organization_id}")
////    public ResponseEntity<List<ProjectDTO>> getProjectByArtistId(@PathVariable Long organization_id) throws NoSuchOrganizationException, NoSuchProjectException, NoSuchArtistException {
//////        Set<Project> projectList = projectService.getProjectByArtistId(organization_id);
////
////        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();
////
//////        List<ProjectDTO> projectDTO = DTOBuilder.buildDtoListForCollection(projectList, ProjectDTO.class, link);
//////        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
////    }

    @GetMapping(value = "/api/project/{project_id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long project_id) throws NoSuchProjectException {
        Project project = projectService.getProject(project_id);
        Link link = linkTo(methodOn(ProjectController.class).getProject(project_id)).withSelfRel();
        System.out.println(link);
        ProjectDTO projectDTO = DTOBuilder.buildDtoForEntity(project, ProjectDTO.class, link);
        return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/project")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();
        List<ProjectDTO> cities = DTOBuilder.buildDtoListForCollection(projectList, ProjectDTO.class, link);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

//    @GetMapping(value = "/api/project/artist/{artist_id}")
//    public ResponseEntity<List<ProjectDTO>> getProjectsByOrganizationID(@PathVariable Long artist_id) throws NoSuchArtistException {
//        Set<Project> projectList = projectService.getProjectByArtistId(artist_id);
//        Link link = linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel();
//        List<ProjectDTO> projectDTO = DTOBuilder.buildDtoListForCollection(projectList, ProjectDTO.class, link);
//        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
//    }

//    @PostMapping(value = "/api/project/artist/{organization_id}")
//    public  ResponseEntity<ProjectDTO> addProject(@RequestBody Project newProject, @PathVariable Long organization_id)
//            throws NoSuchOrganizationException, NoSuchProjectException {
//        projectService.createProject(newProject);
//        Link link = linkTo(methodOn(ProjectController.class).getProject(newProject.getId())).withSelfRel();
//        ProjectDTO projectDTO = DTOBuilder.buildDtoForEntity(newProject, ProjectDTO.class, link);
//        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/api/project/{project_id}/artist/{organization_id}")
    public  ResponseEntity<ProjectDTO> updateProject(@RequestBody Project uProject,
                                                    @PathVariable Long project_id, @PathVariable Long organization_id)
            throws NoSuchOrganizationException, NoSuchProjectException {
        projectService.updateProject(uProject, project_id, organization_id);
        Project project = projectService.getProject(project_id);
        Link link = linkTo(methodOn(ProjectController.class).getProject(project_id)).withSelfRel();
        ProjectDTO projectDTO = DTOBuilder.buildDtoForEntity(project, ProjectDTO.class, link);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

//    @DeleteMapping(value = "/api/project/{project_id}")
//    public  ResponseEntity deleteProject(@PathVariable Long project_id) throws NoSuchProjectException, ExistsArtistsForProjectException, ExistsArtistsForProjectException {
//        projectService.deleteProject(project_id);
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    @PostMapping(value = "/api/project/{project_id}/artist/{artist_id}")
//    public  ResponseEntity<ProjectDTO> addArtistForProject(@PathVariable Long project_id, @PathVariable Long artist_id)
//            throws NoSuchProjectException, NoSuchArtistException, AlreadyExistsArtistInProjectException, ArtistAbsentException {
//        projectService.addArtistForProject(project_id,artist_id);
//        Project project = projectService.getProject(project_id);
//        Link link = linkTo(methodOn(ProjectController.class).getProject(project_id)).withSelfRel();
//        ProjectDTO projectDTO = DTOBuilder.buildDtoForEntity(project, ProjectDTO.class, link);
//        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/api/project/{project_id}/artist/{artist_id}")
//    public  ResponseEntity<ProjectDTO> removeArtistForProject(@PathVariable Long project_id, @PathVariable Long artist_id)
//            throws NoSuchProjectException, NoSuchArtistException, ProjectHasNotArtistException {
//        projectService.removeArtistForProject(project_id,artist_id);
//        Project project = projectService.getProject(project_id);
//        Link link = linkTo(methodOn(ProjectController.class).getProject(project_id)).withSelfRel();
//        ProjectDTO projectDTO = DTOBuilder.buildDtoForEntity(project, ProjectDTO.class, link);
//        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
//    }

}
