package com.crio.jukebox.services;

import java.util.Set;
import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.entities.Playlist;

public interface IPlaylistService {
    CurrentPlayingSongDto playPlaylist(String userId, String playlistId);

    Playlist findPlaylistById(String userId, String playlistId);

    Playlist createPlaylist(String userId, String playlistName, Set<String> songIds);

    void deletePlaylist(String userId, String playlistId);

    PlaylistDto addSongsToPlaylist(String userId, String playlistId, Set<String> songIds);

    PlaylistDto deleteSongsFromPlaylist(String userId, String playlistId, Set<String> songIds);
}
