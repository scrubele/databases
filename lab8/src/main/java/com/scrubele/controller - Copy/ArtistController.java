package com.scrubele.controller;

import com.scrubele.DTO.DTOBuilder;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ArtistController {
    @Autowired
    ArtistService artistService;

//    @GetMapping(value = "/api/artist/project/{project__id}")
//    public ResponseEntity<List<ArtistDTO>> getArtistsByProjectId(@PathVariable Long project__id) throws NoSuchProjectException {
//        Set<Artist> artistList = artistService.getArtistsByProjectId(project__id);
//        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();
//        List<ArtistDTO> projectDTO = DTOBuilder.buildDtoListForCollection(artistList, ArtistDTO.class, link);
//        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
//    }

    @GetMapping(value = "/api/artist/organization/{organization_id}")
    public ResponseEntity<List<ArtistDTO>> getArtistsByOrganizationID(@PathVariable Long organization_id) throws NoSuchOrganizationException, NoSuchArtistException, NoSuchOrganizationException {
        Set<Artist> artistList = artistService.getArtistsByOrganizationID(organization_id);

        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();

        List<ArtistDTO> ArtistDTO = DTOBuilder.buildDtoListForCollection(artistList, ArtistDTO.class, link);
        return new ResponseEntity<>(ArtistDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/artist/{artist_id}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable Long artist_id) throws NoSuchArtistException {
        Artist artist = artistService.getArtist(artist_id);
        System.out.println(artist.toString());
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();
        System.out.println(link);
        ArtistDTO artistDTO = DTOBuilder.buildDtoForEntity(artist, ArtistDTO.class, link);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/artist")
    public ResponseEntity<List<ArtistDTO>> getAllArtists()  {
        List<Artist> artistList = artistService.getAllArtists();
        for (Artist artisti: artistList){
            System.out.println(artisti);
        }
        Link link = linkTo(methodOn(ArtistController.class).getAllArtists()).withSelfRel();
        List<ArtistDTO> artistDTO = DTOBuilder.buildDtoListForCollection(artistList, ArtistDTO.class, link);
        System.out.println(artistDTO.size());
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/artist")
    public  ResponseEntity<ArtistDTO> addArtist(@RequestBody Artist newArtist, Long organization_id) throws NoSuchArtistException, NoSuchOrganizationException {
        artistService.createArtist(newArtist, organization_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(newArtist.getId())).withSelfRel();
        ArtistDTO artistDTO = DTOBuilder.buildDtoForEntity(newArtist, ArtistDTO.class, link);
        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/artist/{artist_id}")
    public  ResponseEntity<ArtistDTO> updateArtist(@RequestBody Artist uArtist, @PathVariable Long artist_id) throws NoSuchArtistException {
        artistService.updateArtist(uArtist,artist_id);
        Artist artist = artistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();
        ArtistDTO artistDTO = DTOBuilder.buildDtoForEntity(artist, ArtistDTO.class, link);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/artist/{artist_id}")
    public  ResponseEntity deleteArtist(@PathVariable Long artist_id) throws ExistsProjectForArtistException, NoSuchArtistException {
        artistService.deleteArtist(artist_id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
