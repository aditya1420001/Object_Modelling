package com.crio.jukebox.commands.Impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand {

    private final IPlaylistService playService;

    public CreatePlaylistCommand(IPlaylistService playService) {
        this.playService = playService;
    }

    @Override
    public void execute(List<String> tokens) {

        final String userId = tokens.get(1);
        final String playlistName = tokens.get(2);
        LinkedHashSet<String> songIds =
                tokens.stream().skip(3).collect(Collectors.toCollection(LinkedHashSet::new));

        playService.createPlaylist(userId, playlistName, songIds);

    }

}
