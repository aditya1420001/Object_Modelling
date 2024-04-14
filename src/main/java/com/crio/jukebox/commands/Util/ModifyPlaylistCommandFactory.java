package com.crio.jukebox.commands.Util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommandFactory {

    private static final String ADD_SONG = "ADD-SONG";
    private static final String DELETE_SONG = "DELETE-SONG";


    public static PlaylistDto getPlaylistDto(IPlaylistService playlistService,
            List<String> tokens) {

        final String command = tokens.get(1);
        final String userId = tokens.get(2);
        final String playlistId = tokens.get(3);
        LinkedHashSet<String> songIds =
                tokens.stream().skip(4).collect(Collectors.toCollection(LinkedHashSet::new));

        PlaylistDto playlist = null;

        switch (command) {
            case ADD_SONG:
                playlist = playlistService.addSongsToPlaylist(userId, playlistId, songIds);
                break;
            case DELETE_SONG:
                playlist = playlistService.deleteSongsFromPlaylist(userId, playlistId, songIds);
                break;
            default:
                break;
        }

        return playlist;
    }


}
