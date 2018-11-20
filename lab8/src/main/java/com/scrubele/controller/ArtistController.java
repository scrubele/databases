package com.scrubele.controller;

import com.scrubele.DTO.impl.ArtistDTO;
import com.scrubele.domain.Artist;
import com.scrubele.exceptions.*;
import com.scrubele.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ArtistController {
    @Autowired
    ArtistService ArtistService;
// get Artist by class id
    @GetMapping(value = "/api/artist/organization/{organization_id}")
    public ResponseEntity<List<ArtistDTO>> getArtistsByOrganizationID(@PathVariable Long organization_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        Set<Artist> ArtistSet = ArtistService.getArtistByOrganizationId(organization_id);

        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();

        List<ArtistDTO> ArtistsDTO = new ArrayList<>();
        for (Artist entity : ArtistSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ArtistDTO dto = new ArtistDTO(entity, selfLink);
            ArtistsDTO.add(dto);
        }

        return new ResponseEntity<>(ArtistsDTO, HttpStatus.OK);
    }
// get Artist
    @GetMapping(value = "/api/artist/{artist_id}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable Long artist_id) throws NoSuchArtistException, NoSuchProjectException, NoSuchOrganizationException {
        Artist artist = ArtistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }
// get all Artists
    @GetMapping(value = "/api/artist")
    public ResponseEntity<Set<ArtistDTO>> getAllArtists() throws NoSuchArtistException, NoSuchProjectException, NoSuchOrganizationException {
        List<Artist> artistSet = ArtistService.getAllArtists();
        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();

        Set<ArtistDTO> artistsDTO = new HashSet<>();
        for (Artist entity : artistSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ArtistDTO dto = new ArtistDTO(entity, selfLink);
            artistsDTO.add(dto);
        }

        return new ResponseEntity<>(artistsDTO, HttpStatus.OK);
    }
// get Artist
    @GetMapping(value = "/api/artist/project/{project_id}")
    public ResponseEntity<Set<ArtistDTO>> getArtistsByProjectID(@PathVariable Long project_id) throws NoSuchProjectException, NoSuchArtistException, NoSuchOrganizationException {
        Set<Artist> ArtistSet = ArtistService.getArtistsByProjectId(project_id);
        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();

        Set<ArtistDTO> ArtistsDTO = new HashSet<>();
        for (Artist entity : ArtistSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ArtistDTO dto = new ArtistDTO(entity, selfLink);
            ArtistsDTO.add(dto);
        }

        return new ResponseEntity<>(ArtistsDTO, HttpStatus.OK);
    }
// add Artist
    @PostMapping(value = "/api/artist/organization/{organization_id}")
    public  ResponseEntity<ArtistDTO> addArtist(@RequestBody Artist newArtist, @PathVariable Long organization_id)
            throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        ArtistService.createArtist(newArtist, organization_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(newArtist.getId())).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(newArtist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }
//update Artist
    @PutMapping(value = "/api/artist/{artist_id}/organization/{organization_id}")
    public  ResponseEntity<ArtistDTO> updateArtist(@RequestBody Artist uArtist,
                                                    @PathVariable Long artist_id, @PathVariable Long organization_id)
            throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        ArtistService.updateArtist(uArtist, artist_id, organization_id);
        Artist Artist = ArtistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/artist/{artist_id}")
    public  ResponseEntity deleteArtist(@PathVariable Long artist_id) throws NoSuchArtistException, ExistsProjectForArtistException, ExistsProjectForArtistException {
        ArtistService.deleteArtist(artist_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/artist/{artist_id}/project/{project_id}")
    public  ResponseEntity<ArtistDTO> addProjectForArtist(@PathVariable Long artist_id, @PathVariable Long project_id)
            throws NoSuchArtistException, NoSuchProjectException, NoSuchOrganizationException, AlreadyExistsProjectInArtistException, ProjectAbsentException {
        ArtistService.addProjectForArtist(artist_id,project_id);
        Artist Artist = ArtistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/artist/{artist_id}/{project_id}")
    public  ResponseEntity<ArtistDTO> removeProjectForArtist(@PathVariable Long artist_id, @PathVariable Long project_id)
            throws NoSuchArtistException, NoSuchProjectException, NoSuchOrganizationException, ArtistHasNotProjectException {
        ArtistService.removeProjectForArtist(artist_id,project_id);
        Artist Artist = ArtistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

}

