package com.crio.jukebox.entities;

import com.crio.jukebox.dataStructure.Node;
import com.crio.jukebox.exception.JukeboxException;

public class PlayPlayList extends BaseEntity {

    private final String playlistId;
    private Node<String> head;
    private Node<String> curr;

    public PlayPlayList(String id) {
        this.playlistId = id;
        head = curr = null;
    }

    public PlayPlayList(Playlist playlist) {
        this(playlist.getId());
        head = curr = playlist.getHead();
        playlist.getTail();
    }

    // returns next song id
    public String next() {
        curr = curr.next;
        return curr.value();
    }

    // returns prev song id
    public String prev() {
        curr = curr.prev;
        return curr.value();
    }

    public String playWithCustomSongId(String songId) {

        Node<String> tmp = head;

        while ((tmp.next != head) && !tmp.value().equals(songId)) {
            tmp = tmp.next;
        }

        if (tmp.value().equals(songId)) {
            curr = tmp;
            return curr.value();
        }

        throw new JukeboxException("Given song id is not a part of the active playlist");
    }

    // returns the playlistId
    public String getPlaylistId() {
        return this.playlistId;
    }

    public String play() {
        return this.curr.value();
    }

}
