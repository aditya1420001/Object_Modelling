package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class User extends BaseUser {

    private Map<String, Playlist> playlistMap;
    private Integer playlistIdIncrementor = 0;
    private PlayPlayList playPlayList;

    public User(String name) {
        super(name);
        this.playlistMap = new HashMap<>();
    }

    public User(String id, String name) {
        this(name);
        this.id = id;
    }

    public Playlist getPlaylistById(String playlistId) {
        return playlistMap.get(playlistId);
    }

    public void setPlayPlaylist(PlayPlayList playPlayList) {
        this.playPlayList = playPlayList;
    }

    public PlayPlayList getPlayPlaylist() {
        return playPlayList;
    }

    public Playlist createPlayList(String playListName, Set<String> songIds) {
        Playlist newPlaylist =
                new Playlist(Integer.toString(++playlistIdIncrementor), playListName, songIds);
        this.playlistMap.put(Integer.toString(playlistIdIncrementor), newPlaylist);
        return newPlaylist;
    }

    public User(String name, Map<String, Playlist> playlistMap) {
        this(name);
        this.playlistMap = playlistMap;
    }

    @Override
    public String toString() {
        return String.format("%s %s", id, name);
    }

    public void deletePlaylistById(String playlistId) {
        playlistMap.remove(playlistId);
    }

}
