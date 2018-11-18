package com.scrubele.service;

import com.scrubele.Repository.OrganizationRepository;
import com.scrubele.Repository.ProjectRepository;
import com.scrubele.domain.Organization;
import com.scrubele.exceptions.ExistsPersonsForCityException;
import com.scrubele.exceptions.NoSuchOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;
    private boolean ascending;

    @Autowired
    ProjectRepository projectRepository;

    public List<Organization> getAllOrganization() {
        return organizationRepository.findAll();
        //List <Organization>  list = new List<Organization>() ;
        //return "Avaanbsv";
    }

    public Organization getOrganization(Long organization_id) throws NoSuchOrganizationException {
//        Organization organization =cityRepository.findOne(organization_id);//1.5.9
        Organization organization = organizationRepository.findById(organization_id).get();//2.0.0.M7
        if (organization == null) throw new NoSuchOrganizationException();
        return organization;
    }

    @Transactional
    public void createOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    @Transactional
    public void updateOrganization(Organization uOrganization, Long organization_id) throws NoSuchOrganizationException {
//        Organization organization = cityRepository.findOne(organization_id);//1.5.9
        Organization organization = organizationRepository.findById(organization_id).get();//2.0.0.M7

        if (organization == null) throw new NoSuchOrganizationException();
        organization.setId(uOrganization.getId());
        organization.setName(uOrganization.getName());
        organization.setPhoneNumber(uOrganization.getPhoneNumber());
        organization.setAddress(uOrganization.getAddress());
        organization.setArtists(uOrganization.getArtists());
        organizationRepository.save(organization);
    }

    @Transactional
    public void deleteOrganization(Long organization_id) throws NoSuchOrganizationException, ExistsPersonsForCityException {
//        Organization organization = cityRepository.findOne(organization_id);//1.5.9
        Organization organization = organizationRepository.findById(organization_id).get();//2.0.0.M7
        if (organization == null) throw new NoSuchOrganizationException();
        if (organization.getArtists().size() != 0) throw new ExistsPersonsForCityException();
        organizationRepository.delete(organization);
    }

}
