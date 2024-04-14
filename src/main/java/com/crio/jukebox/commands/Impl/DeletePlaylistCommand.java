package com.crio.jukebox.commands.Impl;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.commands.ICommand;

public class DeletePlaylistCommand implements ICommand {

    private final IPlaylistService playService;

    public DeletePlaylistCommand(IPlaylistService playService) {
        this.playService = playService;
    }

    @Override
    public void execute(List<String> tokens) {
        final String userId = tokens.get(1);
        final String playlistId = tokens.get(2);

        playService.deletePlaylist(userId, playlistId);
        System.out.println("Delete Successful");
    }

}
