package com.crio.jukebox.services.impl;

import java.util.Set;
import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.entities.PlayPlayList;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exception.JukeboxException;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongPoolService;
import com.crio.jukebox.services.IUserService;

public class PlaylistServiceImpl implements IPlaylistService {

    private final IUserService userService;
    private final IUserRepository userRepository;
    private final ISongPoolService songPoolService;

    public PlaylistServiceImpl(IUserService userService, IUserRepository userRepository,
            ISongPoolService songPoolService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.songPoolService = songPoolService;
    }

    @Override
    public Playlist findPlaylistById(String userId, String playlistId) {
        return userRepository.findPlaylistById(userId, playlistId)
                .orElseThrow(() -> new JukeboxException("Playlist not found"));
    }

    @Override
    public Playlist createPlaylist(String userId, String playlistName, Set<String> songIds) {

        User userEntity = userService.findById(userId);

        // validating if songs are present
        songPoolService.validateSongsIfPresent(songIds);

        Playlist newPlaylist = userEntity.createPlayList(playlistName, songIds);

        userRepository.save(userEntity);

        System.out.println(newPlaylist);
        return newPlaylist;
    }

    @Override
    public CurrentPlayingSongDto playPlaylist(String userId, String playlistId) {
        User user = userService.findById(userId);

        PlayPlayList playPlayList =
                new PlayPlayList(this.findPlaylistById(user.getId(), playlistId));
        user.setPlayPlaylist(playPlayList);

        userRepository.save(user);

        final String songId = user.getPlayPlaylist().play();

        return songPoolService.getSongDto(songId);
    }

    @Override
    public PlaylistDto addSongsToPlaylist(String userId, String playlistId, Set<String> songIds) {

        User user = userService.findById(userId);

        songPoolService.validateSongsIfPresent(songIds);

        Playlist playlistEntity = user.getPlaylistById(playlistId);

        playlistEntity.addSongs(songIds);
        userRepository.save(user);

        return new PlaylistDto(playlistEntity);
    }

    @Override
    public PlaylistDto deleteSongsFromPlaylist(String userId, String playlistId,
            Set<String> songIds) {

        User user = userService.findById(userId);

        Playlist playlistEntity = user.getPlaylistById(playlistId);

        Set<String> availablePlaylistSongs = playlistEntity.getPlaylistSongIds();

        // checking before deletion to obtain trasactional persistance
        Boolean flag = songIds.stream().allMatch(id -> availablePlaylistSongs.contains(id));

        if (!flag)
            throw new JukeboxException(
                    "Some Requested Songs for Deletion are not present in the playlist. Please try again.");

        playlistEntity.removeSongs(songIds);
        userRepository.save(user);

        return new PlaylistDto(playlistEntity);
    }

    @Override
    public void deletePlaylist(String userId, String playlistId) {
        userRepository.deletePlaylistById(userId, playlistId);
    }

}
