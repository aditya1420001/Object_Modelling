package com.crio.jukebox.entities;

public class Artist extends BaseUser {

    public Artist(String name) {
        super(name);
    }

    public Artist(String id, String name) {
        super(name);
        this.id = id;
    }
    
}
