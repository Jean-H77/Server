package org.server;

import org.server.entity.Player;
import org.server.session.impl.PlayerSession;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Player> PLAYERS = new ArrayList<>();
    private final List<PlayerSession> PLAYER_SESSIONS = new ArrayList<>();

    public void addPlayer(Player player) {
        PLAYERS.add(player);
    }
}
