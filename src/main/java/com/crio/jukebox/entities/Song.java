package com.crio.jukebox.entities;

import java.util.Collections;
import java.util.Set;

public class Song extends BaseEntity {

    private final String name;
    private final String genre;
    private final String albumId;
    private final Set<String> featuredArtistsIds;

    public Song(String id, String songName, String genre, String albumId,
            Set<String> featuredArtistsIds) {
        this(songName, genre, albumId, featuredArtistsIds);
        this.id = id;
    }

    public Song(String songName, String genre, String albumId, Set<String> featuredArtistsIds) {
        this.name = songName;
        this.genre = genre;
        this.albumId = albumId;
        this.featuredArtistsIds = featuredArtistsIds;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public Set<String> getFeaturedArtists() {
        return Collections.unmodifiableSet(this.featuredArtistsIds);
    }

}
