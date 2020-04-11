package com.scrubele;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects", schema = "artists")
public class ProjectsEntity {
    private int id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Set<ArtistsEntity> artistsEntitySet = new HashSet<>();

    //private Collection<ArtistProjectsEntity> artistProjectsByProjects;
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date_start", nullable = true)
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "date_end", nullable = true)
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

//    @OneToMany(mappedBy = "projectsByProjects")
//    public Collection<ArtistProjectsEntity> getArtistProjectsByOrganization() {
//        return artistProjectsByProjects;
//    }
//    public void setArtistProjectsByOrganization(Collection<ArtistProjectsEntity> artistProjectsByProjects) {
//        this.artistProjectsByProjects = artistProjectsByProjects;
//    }

    @ManyToMany(targetEntity = ArtistsEntity.class, mappedBy="projects")
    public Set<ArtistsEntity> getArtistsEntitySet() {
        return artistsEntitySet;
    }

    public void setArtistsEntitySet(Set<ArtistsEntity> artistsEntitySet) {
        this.artistsEntitySet = artistsEntitySet;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectsEntity that = (ProjectsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateStart, dateEnd);
    }
    @Override
    public String toString(){
        return "Id= " + id + ", Name= " + name + ", DateStart= " + dateStart
                + ", dateEnd= " + dateEnd ;
    }
}
