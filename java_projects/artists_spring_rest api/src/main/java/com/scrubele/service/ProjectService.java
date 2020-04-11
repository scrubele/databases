package com.scrubele.service;

import com.scrubele.Repository.ArtistRepository;
import com.scrubele.Repository.ProjectRepository;
import com.scrubele.domain.Artist;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.ExistsArtistsForProjectException;
import com.scrubele.exceptions.NoSuchArtistException;
import com.scrubele.exceptions.NoSuchProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository ProjectRepository;

    @Autowired
    ArtistRepository ArtistRepository;

    public Set<Project> getProjectsByArtistId(Long Artist_id) throws NoSuchArtistException {
//        Artist Artist = patientRepository.findOne(patient_id);//1.5.9
        Artist Artist = ArtistRepository.findById(Artist_id).get();//2.0.0.M7
        if (Artist == null) throw new NoSuchArtistException();
        return Artist.getProjects();
    }

    public Project getProject(Long Project_id) throws NoSuchProjectException {
//        Project Project = medicineRepository.findOne(medicine_id);//1.5.9
        Project Project = ProjectRepository.findById(Project_id).get();//2.0.0.M7
        if (Project == null) throw new NoSuchProjectException();
        return Project;
    }

    public List<Project> getAllProjects() {
        return ProjectRepository.findAll();
    }

    @Transactional
    public void createProject(Project Project) {
        ProjectRepository.save(Project);
    }

    @Transactional
    public void updateProject(Project uProject, Long Project_id) throws NoSuchProjectException {
//        Project Project = medicineRepository.findOne(medicine_id);//1.5.9
        Project Project = ProjectRepository.findById(Project_id).get();//2.0.0.M7
        if (Project == null) throw new NoSuchProjectException();
        //update
        Project.setName(uProject.getName());
    }

    @Transactional
    public void deleteProject(Long Project_id) throws NoSuchProjectException, ExistsArtistsForProjectException {
//        Project Project = medicineRepository.findOne(medicine_id);//1.5.9
        Project Project = ProjectRepository.findById(Project_id).get();//2.0.0.M7

        if (Project == null) throw new NoSuchProjectException();
        if (Project.getArtists().size() != 0) throw new ExistsArtistsForProjectException();
        ProjectRepository.delete(Project);
    }
}
