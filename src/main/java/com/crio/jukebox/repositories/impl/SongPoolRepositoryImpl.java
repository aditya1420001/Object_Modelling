package com.crio.jukebox.repositories.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exception.JukeboxException;
import com.crio.jukebox.repositories.ISongPoolRepository;

public class SongPoolRepositoryImpl implements ISongPoolRepository {

    private final Map<String, Song> songPoolMap;
    private Integer id = 0;

    public SongPoolRepositoryImpl() {
        this.songPoolMap = new LinkedHashMap<>();
    }


    @Override
    public void validateSongsIfPresent(Set<String> songIds) {
        Boolean isMatch = songIds.stream().allMatch(id -> Objects.nonNull(songPoolMap.get(id)));
        if (!isMatch) {
            throw new JukeboxException("Some Requested Songs Not Available. Please try again.");
        }
    }

    @Override
    public Song save(Song entity) {
        if (Objects.isNull(entity.getId())) {
            id++;
            entity = new Song(Integer.toString(id), entity.getName(), entity.getGenre(),
                    entity.getAlbumId(), entity.getFeaturedArtists());
        }
        songPoolMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        return songPoolMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songPoolMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return Objects.nonNull(songPoolMap.get(id));
    }

    @Override
    public void delete(Song entity) {
        for (Map.Entry<String, Song> entry : songPoolMap.entrySet()) {
            if (entity.equals(entry.getValue())) {
                songPoolMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        this.songPoolMap.remove(id);
    }

    @Override
    public long count() {
        return songPoolMap.values().stream().count();
    }


}
