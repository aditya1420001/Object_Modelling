package com.crio.jukebox.repositories;

import java.util.Optional;
import com.crio.jukebox.entities.Artist;

public interface IArtistRepository extends CRUDRepository<Artist, String> {
    Optional<Artist> findByName(String name);

    Optional<Artist> findById(String id);
}
