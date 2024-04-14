package com.crio.jukebox.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Album extends BaseEntity {

    private final String name;
    private final String artistOwnerId;
    private Set<String> albumSongIds;

    public Album(String name, String artistOwnerId) {
        this.name = name;
        this.artistOwnerId = artistOwnerId;
        albumSongIds = new HashSet<>();
    }

    public Album(String id, String name, String artistOwnerId) {
        this(name, artistOwnerId);
        this.id = id;
    }

    public String getArtistOwnerId() {
        return artistOwnerId;
    }

    public Set<String> getSongs() {
        return Collections.unmodifiableSet(albumSongIds);
    }

    public String getName() {
        return name;
    }

    public void addSong(String id) {
        this.albumSongIds.add(id);
    }

    public void removeSong(String id) {
        this.albumSongIds.remove(id);
    }

}
