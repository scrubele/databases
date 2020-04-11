package com.scrubele;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Artists", schema = "artists")
public class ArtistsEntity {
    private int id;
    private String surname;
    private String name;
    private String position;
    private OrganizationsEntity organizationsByOrganizations;
    Set<ProjectsEntity> projects = new HashSet<>();


    public ArtistsEntity(String surname, String name, String position, OrganizationsEntity organizationsByOrganizations) {

        this.surname = surname;
        this.name = name;
        this.position = position;
        this.organizationsByOrganizations = organizationsByOrganizations;
    }

    public ArtistsEntity(int id, String surname, String name, String position) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.position = position;
    }

    public ArtistsEntity() {
    }

    //private Collection<ArtistProjectsEntity> artistProjectsByArtists;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @JoinColumn(name = "id_organization", referencedColumnName = "id", nullable = false)
    public OrganizationsEntity getOrganizationsByOrganizations() {
        return organizationsByOrganizations;
    }

    public void setOrganizationsByOrganizations(OrganizationsEntity organizationsByOrganizations) {
        this.organizationsByOrganizations = organizationsByOrganizations;
    }
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "artist_projects",
            joinColumns = { @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "projects_id", referencedColumnName = "id", nullable = false), }
    )
    public Set<ProjectsEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsEntity> projects) {
        this.projects = projects;
    }

    public void addprojectsEntity(ProjectsEntity projectsEntity){
        if(!getProjects().contains(projectsEntity)){
            getProjects().add(projectsEntity);
        }
        if(!projectsEntity.getArtistsEntitySet().contains(this)){
            projectsEntity.getArtistsEntitySet().add(this);
        }
    }

    public void deleteProjectEnitity(ProjectsEntity projectsEntity){
        if(getProjects().contains(projectsEntity)){
            getProjects().remove(projectsEntity);
        }
        if(projectsEntity.getArtistsEntitySet().contains(this)){
            projectsEntity.getArtistsEntitySet().remove(this);
        }
    }


    @Override
    public String toString() {
        return "ArtistsEntity{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", organizationsByOrganizations=" + organizationsByOrganizations.getName() +
                ", projects=" + projects +
                '}';
    }

    public String toStringJoinTable(){
        return "ArtistsEntity{" +
                "id=" + id +
                " projects=" + projects +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsEntity that = (ArtistsEntity) o;
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
