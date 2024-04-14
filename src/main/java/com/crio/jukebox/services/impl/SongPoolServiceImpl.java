package com.crio.jukebox.services.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exception.JukeboxException;
import com.crio.jukebox.repositories.IArtistRepository;
import com.crio.jukebox.repositories.ISongPoolRepository;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.ISongPoolService;

public class SongPoolServiceImpl implements ISongPoolService {

    private final IArtistService artistService;
    private final ISongPoolRepository songPoolRepository;
    private final IAlbumService albumService;

    public SongPoolServiceImpl(ISongPoolRepository songPoolRepository,
            IArtistRepository artistRepository, IAlbumService albumService,
            IArtistService artistService) {
        this.songPoolRepository = songPoolRepository;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    private Song create(String songName, String genre, String albumId,
            Set<String> feauturedArtistsIds) {
        return songPoolRepository.save(new Song(songName, genre, albumId, feauturedArtistsIds));
    }

    @Override
    public void validateSongsIfPresent(Set<String> songIds) {
        songPoolRepository.validateSongsIfPresent(songIds);
    }

    @Override
    public Song findBySongId(String songId) {
        return songPoolRepository.findById(songId).orElseThrow(() -> new JukeboxException());
    }

    @Override
    public Song loadSong(String songName, String genre, String albumName, String albumArtist,
            List<String> feauturedArtists) {

        Artist artistOwner = artistService.loadByName(albumArtist);

        Album albumEntity = albumService.loadByName(albumName, artistOwner.getId());

        LinkedHashSet<String> feauturedArtistsIds =
                feauturedArtists.stream().map(artistService::loadByName).map(Artist::getId)
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        Song songEntity = this.create(songName, genre, albumEntity.getId(), feauturedArtistsIds);

        // updating the list of songs
        albumService.addSongToAlbum(albumEntity.getId(), songEntity.getId());

        return songEntity;
    }

    @Override
    public CurrentPlayingSongDto getSongDto(String songId) {
        Song currentSong = this.findBySongId(songId);
        Album album = albumService.findById(currentSong.getAlbumId());
        LinkedHashSet<String> featuredArtists =
                artistService.findAllByIds(currentSong.getFeaturedArtists()).stream()
                        .map(Artist::getName).collect(Collectors.toCollection(LinkedHashSet::new));

        return new CurrentPlayingSongDto(currentSong.getName(), album.getName(), featuredArtists);
    }


}
