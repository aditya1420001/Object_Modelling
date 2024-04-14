package com.crio.jukebox.services;

import com.crio.jukebox.entities.Album;

public interface IAlbumService {
    Album create(String albumName, String albumOnwerId);

    void addSongToAlbum(String albumId, String songId);

    Album loadByName(String albumName, String artistOwner);

    Album findByName(String albumName);

    Album findById(String albumId);
}
