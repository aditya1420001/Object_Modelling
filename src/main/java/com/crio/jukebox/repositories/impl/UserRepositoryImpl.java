package com.crio.jukebox.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exception.JukeboxException;
import com.crio.jukebox.repositories.IUserRepository;

public class UserRepositoryImpl implements IUserRepository  {

    private final Map<String, User> userMap;
    private Integer id = 0;

    public UserRepositoryImpl() {
        userMap = new HashMap<>();
    }

    @Override
    public User save(User entity) {
        if (Objects.isNull(entity.getId())) {
            id++;
            entity = new User(Integer.toString(id), entity.getName());
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<Playlist> findPlaylistById(String userId, String playlistId) {
        return this.findById(userId).map(user -> user.getPlaylistById(playlistId));
    }

    @Override
    public Optional<User> findByName(String name) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            if (name.equals(entry.getValue().getName())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return Objects.nonNull(userMap.get(id));
    }

    @Override
    public void deletePlaylistById(String userId, String playlistId) {

        User userEntity = Optional.ofNullable(userId)
                .map(id -> userMap.get(id))
                .orElseThrow(() -> new JukeboxException());

        Playlist playlistEntity = Optional.ofNullable(playlistId)
                        .map(userEntity::getPlaylistById)
                        .orElseThrow(() -> new JukeboxException("Playlist Not Found"));

        userEntity.deletePlaylistById(playlistEntity.getId());
        this.save(userEntity);
    }

    @Override
    public void delete(User entity) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            if (entity.equals(entry.getValue())) {
                userMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        this.userMap.remove(id);   
    }

    @Override
    public long count() {
        return userMap.values().stream().count();
    }
    
}
