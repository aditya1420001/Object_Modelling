package com.crio.jukebox.services;

import java.util.List;
import java.util.Set;
import com.crio.jukebox.dto.CurrentPlayingSongDto;
import com.crio.jukebox.entities.Song;

public interface ISongPoolService {
    Song loadSong(String name, String genre, String albumName, String albumArtist,
            List<String> feauturedArtists);

    void validateSongsIfPresent(Set<String> songIds);

    Song findBySongId(String songId);

    CurrentPlayingSongDto getSongDto(String songId);
}
