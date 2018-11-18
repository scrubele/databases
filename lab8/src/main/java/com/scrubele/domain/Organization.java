package com.scrubele.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scrubele.DTO.EntityInterface;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "organizations", schema = "artists")
public class Organization implements EntityInterface {
    private Long id;
    private String name;
    private String address;
    private Integer phoneNumber;

    public Organization(Long id, String name, String address, Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    private Set<Artist> artistsByOrganization;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization(Long id) {
        this.id = id;
    }

    public Organization() {
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

    @OneToMany(
            mappedBy = "organizationsByOrganizations",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    //@JoinColumn(name = "id_organization")
    public Set<Artist> getArtists() {
        return artistsByOrganization;
    }
    public void setArtists(Set<Artist> artistsByOrganization)
    {
        this.artistsByOrganization = artistsByOrganization;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
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
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                // ", artistsByOrganization=" + artistsByOrganization +
                '}';
    }

}
