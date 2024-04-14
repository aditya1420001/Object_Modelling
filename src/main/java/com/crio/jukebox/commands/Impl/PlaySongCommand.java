package com.crio.jukebox.commands.Impl;

import java.util.List;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.services.IUserService;

public class PlaySongCommand implements ICommand {

    private final IUserService userService;

    public PlaySongCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {

        final String userId = tokens.get(1);
        final String task = tokens.get(2);

        try {

            CurrentPlayingSongDto currentPlayingSong =
                    userService.playSongWithCommand(userId, task);

            System.out.println(currentPlayingSong);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
