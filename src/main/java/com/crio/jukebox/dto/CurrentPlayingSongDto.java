package com.crio.jukebox.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class CurrentPlayingSongDto {

    private final String songName;
    private final String albumName;
    private final Set<String> featuredArtists;

    public CurrentPlayingSongDto(String songName, String albumName, Set<String> featuredArtists) {
        this.songName = songName;
        this.albumName = albumName;
        this.featuredArtists = featuredArtists;
    }


    @Override
    public String toString() {
        return "Current Song Playing" + "\nSong - " + songName + "\nAlbum - " + albumName
                + "\nArtists - " + featuredArtists.stream().collect(Collectors.joining(","));
    }

}
