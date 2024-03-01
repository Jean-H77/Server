package org.server.session;

import io.netty.channel.Channel;

public abstract class Session {

    private final Channel channel;

    protected Session(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
