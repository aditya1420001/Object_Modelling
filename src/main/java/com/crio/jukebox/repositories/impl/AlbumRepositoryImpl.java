package com.crio.jukebox.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.repositories.IAlbumRepository;

public class AlbumRepositoryImpl implements IAlbumRepository {

    private final Map<String, Album> albumMap;
    private Integer id = 0;

    public AlbumRepositoryImpl() {
        albumMap = new HashMap<>();
    }

    @Override
    public Optional<Album> findNyName(String name) {
        for (Map.Entry<String, Album> entry : albumMap.entrySet()) {
            if (name.equals(entry.getValue().getName())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    @Override
    public Album save(Album entity) {
        if (Objects.isNull(entity.getId())) {
            id++;
            entity = new Album(Integer.toString(id), entity.getName(), entity.getArtistOwnerId());
        }
        albumMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Album> findAll() {
        return albumMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Album> findById(String id) {
        return Optional.ofNullable(albumMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return Objects.nonNull(albumMap.get(id));
    }

    @Override
    public void delete(Album entity) {
        for (Map.Entry<String, Album> entry : albumMap.entrySet()) {
            if (entity.equals(entry.getValue())) {
                albumMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        this.albumMap.remove(id);
    }

    @Override
    public long count() {
        return albumMap.values().stream().count();
    }

}
