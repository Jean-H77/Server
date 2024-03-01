package org.server.entity;

public class Player extends Entity {

    private final String username;
    private final String password;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
