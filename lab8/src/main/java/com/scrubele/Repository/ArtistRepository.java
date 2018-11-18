package com.scrubele.Repository;

import com.scrubele.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
