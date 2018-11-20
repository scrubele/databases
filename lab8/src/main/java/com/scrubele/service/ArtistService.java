package com.scrubele.service;

import com.scrubele.Repository.OrganizationRepository;
import com.scrubele.Repository.ArtistRepository;
import com.scrubele.Repository.ProjectRepository;
import com.scrubele.domain.Organization;
import com.scrubele.domain.Artist;
import com.scrubele.domain.Project;
import com.scrubele.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ArtistService {
    @Autowired
    ArtistRepository ArtistRepository;

    @Autowired
    OrganizationRepository OrganizationRepository;

    @Autowired
    ProjectRepository ProjectRepository;

    public Set<Artist> getArtistByOrganizationId(Long class_id) throws NoSuchOrganizationException {
//        Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Organization Organization = OrganizationRepository.findById(class_id).get();//2.0.0.M7
        if (Organization == null) throw new NoSuchOrganizationException();
        return Organization.getArtists();
    }

    public Artist getArtist(Long Artist_id) throws NoSuchArtistException {
//        Artist Artist = patientRepository.findOne(patient_id);//1.5.9
        Artist Artist = ArtistRepository.findById(Artist_id).get();//2.0.0.M7
        if (Artist == null) throw new NoSuchArtistException();
        return Artist;
    }

    public List<Artist> getAllArtists() {
        return ArtistRepository.findAll();
    }

    public Set<Artist> getArtistsByProjectId(Long Project_id) throws NoSuchProjectException {
//        Project Project = medicineRepository.findOne(medicine_id);//1.5.9
        Project Project = ProjectRepository.findById(Project_id).get();//2.0.0.M7
        if (Project == null) throw new NoSuchProjectException();
        return Project.getArtists();
    }

    @Transactional
    public void createArtist(Artist Artist, Long Organization_id) throws NoSuchOrganizationException {
        if (Organization_id > 0) {
//            Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
            Organization Organization = OrganizationRepository.findById(Organization_id).get();//2.0.0.M7
            if (Organization == null) throw new NoSuchOrganizationException();
            Artist.setOrganizationsByOrganizations(Organization);
        }
        ArtistRepository.save(Artist);
    }

    @Transactional
    public void updateArtist(Artist uArtist, Long Artist_id, Long class_id) throws NoSuchOrganizationException, NoSuchArtistException {
//        Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Organization Organization = OrganizationRepository.findById(class_id).get();//2.0.0.M7
        if (class_id > 0) {
            if (Organization == null) throw new NoSuchOrganizationException();
        }
//        Artist Artist = patientRepository.findOne(patient_id);//1.5.9
        Artist Artist = ArtistRepository.findById(Artist_id).get();//2.0.0.M7
        if (Artist == null) throw new NoSuchArtistException();
        //update
        Artist.setSurname(uArtist.getSurname());
        Artist.setName(uArtist.getName());
        Artist.setPosition(uArtist.getPosition());
        if (class_id > 0) Artist.setOrganizationsByOrganizations(Organization);
        else Artist.setOrganizationsByOrganizations(null);
        ArtistRepository.save(Artist);
    }

    @Transactional
    public void deleteArtist(Long Artist_id) throws NoSuchArtistException, ExistsProjectForArtistException {
//        Artist Artist = patientRepository.findOne(patient_id);//1.5.9
        Artist Artist = ArtistRepository.findById(Artist_id).get();//2.0.0.M7
        if (Artist == null) throw new NoSuchArtistException();
        if (Artist.getProjects().size() != 0) throw new ExistsProjectForArtistException();
        ArtistRepository.delete(Artist);
    }

    @Transactional
    public void addProjectForArtist(Long Artist_id, Long Project_id)
            throws NoSuchArtistException, NoSuchProjectException, AlreadyExistsProjectInArtistException, ProjectAbsentException {
//        Artist Artist = patientRepository.findOne(patient_id);//1.5.9
        Artist Artist = ArtistRepository.findById(Artist_id).get();//2.0.0.M7
        if (Artist == null) throw new NoSuchArtistException();
//        Project Project = medicineRepository.findOne(medicine_id);//1.5.9
        Project Project = ProjectRepository.findById(Project_id).get();//2.0.0.M7
        if (Project == null) throw new NoSuchProjectException();
        if (Artist.getProjects().contains(Project) == true) throw new AlreadyExistsProjectInArtistException();
        Artist.getProjects().add(Project);
        ArtistRepository.save(Artist);
    }

    @Transactional
    public void removeProjectForArtist(Long Artist_id, Long Project_id)
            throws NoSuchArtistException, NoSuchProjectException, ArtistHasNotProjectException {
//        Artist Artist = patientRepository.findOne(patient_id);//1.5.9
        Artist Artist = ArtistRepository.findById(Artist_id).get();//2.0.0.M7
        if (Artist == null) throw new NoSuchArtistException();
//        Project Project = medicineRepository.findOne(medicine_id);//1.5.9
        Project Project = ProjectRepository.findById(Project_id).get();//2.0.0.M7
        if (Project == null) throw new NoSuchProjectException();
        if (Artist.getProjects().contains(Project) == false) throw new ArtistHasNotProjectException();
        Artist.getProjects().remove(Project);
        ArtistRepository.save(Artist);
    }
}
