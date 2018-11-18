package com.scrubele.service;

import com.scrubele.Repository.*;
import com.scrubele.domain.*;
import com.scrubele.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ArtistRepository artistRepository;


    public Project getProject(Long project_id) throws NoSuchProjectException {
//        Project project = ProjectRepository.findOne(project_id);//1.5.9
        Project project = projectRepository.findById(project_id).get();//2.0.0.M7
        if (project == null) throw new NoSuchProjectException();
        return project;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

//    public Set<Project> getProjectByArtistId(Long artist_id) throws NoSuchArtistException {
////        Artist artist = ArtistRepository.findOne(artist_id);//1.5.9
//        Artist artist = artistRepository.findById(artist_id).get();//2.0.0.M7
//        if (artist == null) throw new NoSuchArtistException();
//        return artist.getProjects();
//  }
//

    @Transactional
    public void updateProject(Project uProject, Long project_id, Long artist_id) throws NoSuchOrganizationException, NoSuchProjectException {
//        Organization organization = artistRepository.findOne(artist_id);//1.5.9
         Artist artist = artistRepository.findById(project_id).get();//2.0.0.M7

        if (artist_id > 0) {
            if (artist == null) throw new NoSuchOrganizationException();
        }
//        Project project = ProjectRepository.findOne(project_id);//1.5.9
        Project project = projectRepository.findById(project_id).get();//2.0.0.M7
        if (project == null) throw new NoSuchProjectException();
        //update
        project.setId(uProject.getId());
        project.setName(uProject.getName());
        project.setDateEnd(uProject.getDateEnd());
        project.setDateStart(uProject.getDateStart());
        projectRepository.save(project);
    }

//    @Transactional
//    public void deleteProject(Long project_id) throws NoSuchProjectException, ExistsArtistsForProjectException {
////        Project project = ProjectRepository.findOne(project_id);//1.5.9
//        Project project = projectRepository.findById(project_id).get();//2.0.0.M7
//        if (project == null) throw new NoSuchProjectException();
//        if (project.getArtists().size() != 0) throw new ExistsArtistsForProjectException();
//        projectRepository.delete(project);
//    }
//    @Transactional
//    public void createProject(Project project) {
//        projectRepository.save(project);
//    }
//    @Transactional
//    public void addArtistForProject(Long project_id, Long artist_id)
//            throws NoSuchProjectException, NoSuchArtistException, AlreadyExistsArtistInProjectException, ArtistAbsentException {
////        Project project = ProjectRepository.findOne(project_id);//1.5.9
//        Project project = projectRepository.findById(project_id).get();//2.0.0.M7
//        if (project == null) throw new NoSuchProjectException();
////        Artist artist = ArtistRepository.findOne(artist_id);//1.5.9
//        Artist artist = artistRepository.findById(artist_id).get();//2.0.0.M7
//        if (artist == null) throw new NoSuchArtistException();
//        if (project.getArtists().contains(artist) == true) throw new AlreadyExistsArtistInProjectException();
//
//        project.getArtists().add(artist);
//        projectRepository.save(project);
//    }
//
//    @Transactional
//    public void removeArtistForProject(Long project_id, Long artist_id)
//            throws NoSuchProjectException, NoSuchArtistException, ProjectHasNotArtistException {
////        Project project = ProjectRepository.findOne(project_id);//1.5.9
//        Project project = projectRepository.findById(project_id).get();//2.0.0.M7
//        if (project == null) throw new NoSuchProjectException();
////        Artist artist = ArtistRepository.findOne(artist_id);//1.5.9
//        Artist artist = artistRepository.findById(artist_id).get();//2.0.0.M7
//        if (artist == null) throw new NoSuchArtistException();
//        if (project.getArtists().contains(artist) == false) throw new ProjectHasNotArtistException();
//        project.getArtists().remove(artist);
//        projectRepository.save(project);
//    }


}
