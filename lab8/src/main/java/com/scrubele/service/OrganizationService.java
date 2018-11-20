package com.scrubele.service;

import com.scrubele.Repository.OrganizationRepository;
import com.scrubele.Repository.ArtistRepository;
import com.scrubele.domain.Organization;
import com.scrubele.exceptions.ExistsArtistsForOrganizationException;
import com.scrubele.exceptions.NoSuchOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository OrganizationRepository;
    private boolean ascending;

    @Autowired
    ArtistRepository ArtistRepository;

    public List<Organization> getAllOrganization() {
        return OrganizationRepository.findAll();
    }

    public Organization getOrganization(Long class_id) throws NoSuchOrganizationException {
//        Organization Organization =diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Organization organization = OrganizationRepository.findById(class_id).get();//2.0.0.M7
        System.out.println(organization);
        if (organization == null) throw new NoSuchOrganizationException();
        return organization;
    }

    @Transactional
    public void createOrganization(Organization organization) {
        OrganizationRepository.save(organization);
    }

    @Transactional
    public void updateOrganization(Organization uOrganization, Long class_id) throws NoSuchOrganizationException {
//        Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Organization organization = OrganizationRepository.findById(class_id).get();//2.0.0.M7

        if (organization == null) throw new NoSuchOrganizationException();
        organization.setArtists(uOrganization.getArtists());
        OrganizationRepository.save(organization);
    }

    @Transactional
    public void deleteOrganization(Long class_id) throws NoSuchOrganizationException, ExistsArtistsForOrganizationException {
//        Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Organization organization = OrganizationRepository.findById(class_id).get();//2.0.0.M7
        if (organization == null) throw new NoSuchOrganizationException();
        if (organization.getArtists().size() != 0) throw new ExistsArtistsForOrganizationException();
        OrganizationRepository.delete(organization);
    }


}
