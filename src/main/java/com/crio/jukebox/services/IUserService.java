package com.crio.jukebox.services;

import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.entities.User;

public interface IUserService {
    User create(String userName);

    User loadByName(String userName);

    User findById(String userId);

    CurrentPlayingSongDto playSongWithCommand(String userId, String task);
}
