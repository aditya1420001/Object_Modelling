package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import com.crio.jukebox.dataStructure.Node;

public class Playlist extends BaseEntity {

    private final String name;
    private Node<String> head;
    private Node<String> tail;
    private Map<String, Boolean> songAvailabilityMap;

    private Playlist(String name) {
        this.name = name;
        head = tail = null;
        songAvailabilityMap = new HashMap<>();
    }

    public Playlist(String name, Set<String> songPlaylistIds) {
        this(name);
        this.addSongs(songPlaylistIds);
    }

    public Playlist(String id, String name, Set<String> songPlaylistIds) {
        this(name, songPlaylistIds);
        this.id = id;
    }

    public void addSongs(Set<String> songIds) {
        songIds.stream().filter(songId -> !songAvailabilityMap.getOrDefault(songId, false))
                .forEach(this::insertSongIdNodes);
    }

    public Set<String> getPlaylistSongIds() {

        Set<String> playlistSongIds = new LinkedHashSet<>();

        Node<String> tmp = head;

        if (Objects.nonNull(tmp)) {
            if (head == tail) {
                playlistSongIds.add(tmp.value());
                return playlistSongIds;
            }

            while (tmp.next != head) {
                playlistSongIds.add(tmp.value());
                tmp = tmp.next;
            }
            playlistSongIds.add(tmp.value());
        }

        return playlistSongIds;
    }

    private void insertSongIdNodes(String songId) {
        Node<String> newNode = new Node<>(songId);

        if (Objects.isNull(head) && Objects.isNull(tail)) {
            head = tail = newNode;
            head.next = head.prev = tail;
            tail.next = tail.prev = head;
        } else {
            // settingUp newNode - headNode
            newNode.next = tail.next;
            head.prev = newNode;

            // setting up the tailNode - newNode
            tail.next = newNode;
            newNode.prev = tail;

            // updating new tailNode
            tail = tail.next;
        }
        songAvailabilityMap.put(songId, true); // marking the song as present in the playlist
    }

    public void removeSongs(Set<String> songsIds) {

        songsIds.stream().filter(songId -> songAvailabilityMap.getOrDefault(songId, false))
                .forEach(songId -> {

                    if (Objects.isNull(head))
                        return; // throw exception

                    if (songId.equals(head.value())) {

                        if (head.next == head) {
                            // If only one element is present in the linked list
                            head = null;
                            return;
                        } else {
                            // if multiple elements are present in linked list
                            head.next.prev = tail;
                            head = head.next;
                            tail.next = head;
                            return;
                        }
                    }

                    Node<String> tmp = head;
                    while (tmp.next != head && !tmp.value().equals(songId)) {
                        tmp = tmp.next;
                    }

                    if (tmp.value().equals(songId)) {
                        tmp.next.prev = tmp.prev;
                        tmp.prev.next = tmp.next;

                        if (tmp == tail) {
                            // checking if the tail node is being removed. If yes it will be linked
                            // back to the chain
                            tail = tmp.prev;
                        }
                    }

                    songAvailabilityMap.remove(songId); // removing the song id from the
                                                        // availability list
                });


    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public Node<String> getHead() {
        return this.head;
    }

    public Node<String> getTail() {
        return this.tail;
    }

    @Override
    public String toString() {
        return "Playlist ID - " + id;
    }

}
