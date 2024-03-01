package org.server.session.impl;

import io.netty.channel.Channel;
import org.server.services.handshake.HandshakeRequest;
import org.server.session.Session;

public class HandshakeSession extends Session {

    private final HandshakeRequest request;

    public HandshakeSession(Channel channel, HandshakeRequest request) {
        super(channel);
        this.request = request;
    }

    public HandshakeRequest getRequest() {
        return request;
    }
}
