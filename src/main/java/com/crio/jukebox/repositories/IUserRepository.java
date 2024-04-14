package com.crio.jukebox.repositories;

import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User, String> {
    Optional<User> findByName(String name);

    void deletePlaylistById(String userId, String playlistId);

    Optional<Playlist> findPlaylistById(String userId, String playlistId);
}
