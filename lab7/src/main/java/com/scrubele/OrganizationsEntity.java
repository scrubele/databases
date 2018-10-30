package com.scrubele;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "organizations", schema = "artists")
public class OrganizationsEntity {
    private int id;
    private String name;
    private String address;
    private Integer phoneNumber;
    private Collection<ArtistsEntity> artistByOrganization;
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrganizationsEntity(int id) {
        this.id = id;
    }

    public OrganizationsEntity() {
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
    @Column(name = "address", nullable = true, length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone_number", nullable = true)
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(mappedBy = "organizationsByOrganizations")
    public Collection<ArtistsEntity> getArtistByOrganization() {
        return artistByOrganization;
    }


    public void setArtistByOrganization(Collection<ArtistsEntity> artistByOrganization) {
        this.artistByOrganization = artistByOrganization;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationsEntity that = (OrganizationsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phoneNumber);
    }
    @Override
    public String toString() {
        return "OrganizationsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
               // ", artistByOrganization=" + artistByOrganization +
                '}';
    }

}
