package com.crio.jukebox.services.impl;

import com.crio.jukebox.exception.JukeboxException;
import com.crio.jukebox.repositories.IAlbumRepository;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.services.IAlbumService;

public class AlbumServiceImpl implements IAlbumService {

    private final IAlbumRepository albumRepository;

    public AlbumServiceImpl(IAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album create(String albumName, String albumOnwerId) {
        return albumRepository.save(new Album(albumName, albumOnwerId));
    }

    @Override
    public Album loadByName(String albumName, String artistOwnerId) {
        return albumRepository.findNyName(albumName)
                .orElseGet(() -> this.create(albumName, artistOwnerId));
    }

    @Override
    public Album findByName(String albumName) {
        return albumRepository.findNyName(albumName).orElseThrow(() -> new JukeboxException());
    }

    @Override
    public void addSongToAlbum(String albumId, String songId) {
        Album entity = albumRepository.findById(albumId).map(album -> {
            album.addSong(songId);
            return album;
        }).orElseThrow(() -> new JukeboxException());
        albumRepository.save(entity);
    }

    @Override
    public Album findById(String albumId) {
        return albumRepository.findById(albumId).orElseThrow(() -> new JukeboxException());
    }

}
