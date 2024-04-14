package com.crio.jukebox.services.impl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.repositories.IArtistRepository;
import com.crio.jukebox.services.IArtistService;

public class ArtistServiceImpl implements IArtistService {

    private final IArtistRepository artistRepository;

    public ArtistServiceImpl(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist create(String artistName) {
        return artistRepository.save(new Artist(artistName));
    }

    public Artist loadByName(String artistOwner) {
        return artistRepository.findByName(artistOwner).orElseGet(() -> this.create(artistOwner));
    }

    public Optional<Artist> findByName(String albumName) {
        return artistRepository.findByName(albumName);
    }

    @Override
    public Set<Artist> findAllByIds(Set<String> artistIds) {
        LinkedHashSet<Artist> collect = artistIds.stream().map(artistRepository::findById)
                .map(Optional::orElseThrow).map(Artist.class::cast)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return collect;
    }
}
