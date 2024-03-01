package org.server.session.impl;

import io.netty.channel.Channel;
import org.server.entity.Player;
import org.server.session.Session;

public class PlayerSession extends Session {

    public PlayerSession(Channel channel, Player player) {
        super(channel);
        this.player = player;
    }

    private final Player player;

}
