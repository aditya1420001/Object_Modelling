package com.crio.jukebox.dto;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlaylistDto {

    private final String playlistId;
    private final String playlistName;
    private final Set<String> songIds;

    public PlaylistDto(String playlistId, String playlistName, Set<String> songIds) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songIds = songIds.stream().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public PlaylistDto(Playlist playlist) {
        this(playlist.getId(), playlist.getName(), playlist.getPlaylistSongIds());
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public Set<String> getPlaylistSongIds() {
        return songIds;
    }

    @Override
    public String toString() {
        return "Playlist ID - " + playlistId + "\nPlaylist Name - " + playlistName + "\nSong IDs - "
                + songIds.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }



}
