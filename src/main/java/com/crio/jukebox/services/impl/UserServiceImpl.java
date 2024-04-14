package com.crio.jukebox.services.impl;

import java.util.Objects;
import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.entities.PlayPlayList;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exception.JukeboxException;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.services.ISongPoolService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.impl.FactoryUtils.PlaySongFactory;

public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final ISongPoolService songPoolService;

    public UserServiceImpl(IUserRepository userRepository, ISongPoolService songPoolService) {
        this.userRepository = userRepository;
        this.songPoolService = songPoolService;
    }


    @Override
    public User create(String name) {
        return userRepository.save(new User(name));
    }

    @Override
    public User loadByName(String userName) {
        return userRepository.findByName(userName).orElseGet(() -> this.create(userName));
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new JukeboxException());
    }


    @Override
    public CurrentPlayingSongDto playSongWithCommand(String userId, String command) {
        User user = this.findById(userId);

        PlayPlayList playPlayList = user.getPlayPlaylist();

        if (Objects.isNull(playPlayList))
            throw new JukeboxException("Playlist Not Found");

        String songId = PlaySongFactory.getPlaySongFactory(playPlayList, command);

        userRepository.save(user);

        return songPoolService.getSongDto(songId);
    }

}
