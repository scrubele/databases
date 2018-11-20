package com.scrubele.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scrubele.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artists", schema = "artists")
public class Artist implements EntityInterface {
    private Long id;
    private String surname;
    private String name;
    private String position;
    private Organization organizationsByOrganizations;
    Set<Project> projects = new HashSet<>();


    public Artist(String surname, String name, String position, Organization organizationsByOrganizations) {

        this.surname = surname;
        this.name = name;
        this.position = position;
       this.organizationsByOrganizations = organizationsByOrganizations;
    }

    public Artist(Long id, String surname, String name, String position) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.position = position;
    }

    public Artist() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 50)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "id_organization",  referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Organization getOrganizationsByOrganizations() {
        return organizationsByOrganizations;
    }

    public void setOrganizationsByOrganizations(Organization organizationsByOrganizations) {
        this.organizationsByOrganizations = organizationsByOrganizations;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "artist_projects",
            joinColumns = { @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "projects_id", referencedColumnName = "id", nullable = false), }
    )
    @JsonIgnore
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project){
        if(!getProjects().contains(project)){
            getProjects().add(project);
        }
        if(!project.getArtists().contains(this)){
            project.getArtists().add(this);
        }
    }

    public void deleteProjectEnitity(Project project){
        if(getProjects().contains(project)){
            getProjects().remove(project);
        }
        if(project.getArtists().contains(this)){
            project.getArtists().remove(this);
        }
    }


    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
              // ", Organizations=" + organizationsByOrganizations.getName() +
//                ", projects=" + projects +
                '}';
    }

    public String toStringJoinTable(){
        return "Artist{" +
                "id=" + id +
//                " projects=" + projects +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist that = (Artist) o;
        return id == that.id &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, position);
    }


}
