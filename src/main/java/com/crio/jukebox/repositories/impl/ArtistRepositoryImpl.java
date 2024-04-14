package com.crio.jukebox.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.repositories.IArtistRepository;

public class ArtistRepositoryImpl implements IArtistRepository {

    private final Map<String, Artist> artistMap;
    private static Integer id = 0;

    public ArtistRepositoryImpl() {
        this.artistMap = new HashMap<>();
    }

    @Override
    public Optional<Artist> findByName(String name) {
        for (Map.Entry<String, Artist> entry : artistMap.entrySet()) {
            if (name.equals(entry.getValue().getName())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    @Override
    public Artist save(Artist entity) {
        if (Objects.isNull(entity.getId())) {
            id++;
            entity = new Artist(Integer.toString(id), entity.getName());
        }
        artistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Artist> findAll() {
        return artistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Artist> findById(String id) {
        return Optional.of(artistMap).map(map -> map.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return (artistMap.get(id) != null);
    }

    @Override
    public void delete(Artist entity) {
        for (Map.Entry<String, Artist> entry : artistMap.entrySet()) {
            if (entry.getValue().equals(entity)) {
                artistMap.remove(entry.getKey(), entity);
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        artistMap.remove(id);
    }

    @Override
    public long count() {
        return artistMap.size();
    }

}
