package com.crio.jukebox.repositories;

import java.util.Set;
import com.crio.jukebox.entities.Song;

public interface ISongPoolRepository extends CRUDRepository<Song, String> {
    void validateSongsIfPresent(Set<String> songIds);
}
