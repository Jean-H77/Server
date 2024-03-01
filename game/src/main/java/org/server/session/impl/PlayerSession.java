package org.server.session.impl;

import io.netty.channel.Channel;
import org.server.session.Session;

public class PlayerSession extends Session {

    public PlayerSession(Channel channel) {
        super(channel);
    }

}
