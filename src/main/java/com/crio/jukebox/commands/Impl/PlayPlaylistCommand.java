package com.crio.jukebox.commands.Impl;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.dto.CurrentPlayingSongDto;

public class PlayPlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        final String userId = tokens.get(1);
        final String playlistId = tokens.get(2);

        CurrentPlayingSongDto currentPlayingSong = playlistService.playPlaylist(userId, playlistId);

        System.out.println(currentPlayingSong);
    }


}
