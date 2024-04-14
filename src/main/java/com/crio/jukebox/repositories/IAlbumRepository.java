package com.crio.jukebox.repositories;

import java.util.Optional;
import com.crio.jukebox.entities.Album;

public interface IAlbumRepository extends CRUDRepository<Album, String> {
    Optional<Album> findNyName(String name);
}
