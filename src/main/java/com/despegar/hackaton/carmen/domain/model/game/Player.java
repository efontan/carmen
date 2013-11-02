package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;

public class Player
    implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String genre;

    public Player() {
    }

    public Player(String name, String email, String genre) {
        this.name = name;
        this.email = email;
        this.genre = genre;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
