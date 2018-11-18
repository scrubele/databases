package com.scrubele.service;

import com.scrubele.Repository.ArtistRepository;
import com.scrubele.Repository.OrganizationRepository;
import com.scrubele.Repository.ProjectRepository;
import com.scrubele.domain.Artist;
import com.scrubele.domain.Organization;
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
    ArtistRepository artistRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    OrganizationRepository organizationRepository;
//
//    public Set<Artist> getArtistsByProjectId(Long Artist_id) throws NoSuchProjectException {
////        Project project = projectRepository.findOne(Artist_id);//1.5.9
//        Project project = projectRepository.findById(Artist_id).get();//2.0.0.M7
//        System.out.println("Project"+project.toString());
//        if (project == null) throw new NoSuchProjectException();
//        return project.getArtists();
//    }

    public Artist getArtist(Long artist_id) throws NoSuchArtistException {
//        Artist artist = artistRepository.findOne(artist_id);//1.5.9
        Artist artist = artistRepository.findById(artist_id).get();//2.0.0.M7
        System.out.println("Artist"+artist.toString());
        if (artist == null) throw new NoSuchArtistException();
        return artist;
    }
    public Set<Artist> getArtistsByOrganizationID(Long organization_id) throws NoSuchOrganizationException {
//        Organization Organization = organizationRepository.findOne(Organization_id);//1.5.9
        Organization organization = organizationRepository.findById(organization_id).get();//2.0.0.M7
        System.out.println(organization);
        System.out.println(organization.getArtists());
        if (organization == null) throw new NoSuchOrganizationException();
        return organization.getArtists();
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Transactional
    public void createArtist(Artist artist, Long organization_id) throws NoSuchOrganizationException {
        if (organization_id > 0) {
           /// Organization organization = artistRepository.findOne(artist_id);//1.5.9
            Organization organization = organizationRepository.findById(organization_id).get();//2.0.0.M7

            if (organization == null) throw new NoSuchOrganizationException();
            artist.setOrganizationsByOrganizations(organization);
        }
        artistRepository.save(artist);
    }

    @Transactional
    public void updateArtist(Artist uArtist, Long artist_id) throws NoSuchArtistException {
//        Artist artist = artistRepository.findOne(artist_id);//1.5.9
        Artist artist = artistRepository.findById(artist_id).get();//2.0.0.M7
        if (artist == null) throw new NoSuchArtistException();
        //update
        artist.setId(uArtist.getId());
        artist.setName(uArtist.getName());
        artist.setPosition(uArtist.getPosition());
//        artist.setProjects(uArtist.getProjects());
        artist.setSurname(uArtist.getSurname());
    }

    @Transactional
    public void deleteArtist(Long artist_id) throws NoSuchArtistException, ExistsProjectForArtistException {
//        Artist artist = artistRepository.findOne(artist_id);//1.5.9
        Artist artist = artistRepository.findById(artist_id).get();//2.0.0.M7

        if (artist == null) throw new NoSuchArtistException();
      //  if (artist.getProjects().size() != 0) throw new ExistsProjectForArtistException();
        artistRepository.delete(artist);
    }

    }
