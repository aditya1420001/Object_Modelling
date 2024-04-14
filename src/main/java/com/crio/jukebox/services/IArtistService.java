package com.crio.jukebox.services;

import java.util.Set;
import com.crio.jukebox.entities.Artist;

public interface IArtistService {
    Artist create(String artistName);

    Artist loadByName(String artistOwner);

    Set<Artist> findAllByIds(Set<String> artistIds);
}
