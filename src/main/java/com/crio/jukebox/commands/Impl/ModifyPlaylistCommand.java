package com.crio.jukebox.commands.Impl;

import java.util.List;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.commands.Util.ModifyPlaylistCommandFactory;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playService) {
        this.playlistService = playService;
    }

    @Override
    public void execute(List<String> tokens) {

        PlaylistDto playlist = ModifyPlaylistCommandFactory.getPlaylistDto(playlistService, tokens);

        System.out.println(playlist);

    }

}
