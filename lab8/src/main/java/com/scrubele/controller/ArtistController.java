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
    @GetMapping(value = "/api/artist/Organization/{organization_id}")
    public ResponseEntity<List<ArtistDTO>> getArtistsByOrganizationID(@PathVariable Long class_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        List<Artist> ArtistSet = ArtistService.getArtistByOrganizationId(class_id);

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
    @GetMapping(value = "/api/artist/{Artist_id}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable Long Artist_id) throws NoSuchArtistException, NoSuchProjectException {
        Artist Artist = ArtistService.getArtist(Artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(Artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }
// get all Artists
    @GetMapping(value = "/api/artist")
    public ResponseEntity<List<ArtistDTO>> getAllArtists() throws NoSuchArtistException, NoSuchProjectException {
        List<Artist> artistSet = ArtistService.getAllArtists();
        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();

        List<ArtistDTO> artistsDTO = new ArrayList<>();
        for (Artist entity : artistSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ArtistDTO dto = new ArtistDTO(entity, selfLink);
            artistsDTO.add(dto);
        }

        return new ResponseEntity<>(artistsDTO, HttpStatus.OK);
    }
// get Artist
    @GetMapping(value = "/api/artist/project/{Project_id}")
    public ResponseEntity<List<ArtistDTO>> getArtistsByProjectID(@PathVariable Long Project_id) throws NoSuchProjectException, NoSuchArtistException {
        List<Artist> ArtistSet = ArtistService.getArtistsByProjectId(Project_id);
        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();

        List<ArtistDTO> ArtistsDTO = new ArrayList<>();
        for (Artist entity : ArtistSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ArtistDTO dto = new ArtistDTO(entity, selfLink);
            ArtistsDTO.add(dto);
        }

        return new ResponseEntity<>(ArtistsDTO, HttpStatus.OK);
    }
// add Artist
    @PostMapping(value = "/api/artist/Organization/{organization_id}")
    public  ResponseEntity<ArtistDTO> addArtist(@RequestBody Artist newArtist, @PathVariable Long class_id)
            throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        ArtistService.createArtist(newArtist, class_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(newArtist.getId())).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(newArtist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }
//update Artist
    @PutMapping(value = "/api/artist/{Artist_id}/Organization/{Organization_id}")
    public  ResponseEntity<ArtistDTO> updateArtist(@RequestBody Artist uArtist,
                                                    @PathVariable Long Artist_id, @PathVariable Long class_id)
            throws NoSuchOrganizationException, NoSuchArtistException, NoSuchProjectException {
        ArtistService.updateArtist(uArtist, Artist_id, class_id);
        Artist Artist = ArtistService.getArtist(Artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(Artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/artist/{Artist_id}")
    public  ResponseEntity deleteArtist(@PathVariable Long Artist_id) throws NoSuchArtistException, ExistsProjectForArtistException, ExistsProjectForArtistException {
        ArtistService.deleteArtist(Artist_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/artist/{Artist_id}/project/{Project_id}")
    public  ResponseEntity<ArtistDTO> addProjectForArtist(@PathVariable Long Artist_id, @PathVariable Long Project_id)
            throws NoSuchArtistException, NoSuchProjectException, AlreadyExistsProjectInArtistException, ProjectAbsentException {
        ArtistService.addProjectForArtist(Artist_id,Project_id);
        Artist Artist = ArtistService.getArtist(Artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(Artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/artist/{Artist_id}//{Project_id}")
    public  ResponseEntity<ArtistDTO> removeProjectForArtist(@PathVariable Long Artist_id, @PathVariable Long Project_id)
            throws NoSuchArtistException, NoSuchProjectException, ArtistHasNotProjectException {
        ArtistService.removeProjectForArtist(Artist_id,Project_id);
        Artist Artist = ArtistService.getArtist(Artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(Artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(Artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

}

