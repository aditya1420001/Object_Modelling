package com.crio.jukebox.entities;

public class BaseUser extends BaseEntity {

    protected final String name;

    public BaseUser(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
}
