package org.server.session.impl;

import io.netty.channel.Channel;
import org.server.services.login.LoginRequest;
import org.server.session.Session;

public class LoginSession extends Session {

    private final LoginRequest loginRequest;

    protected LoginSession(Channel channel, LoginRequest loginRequest) {
        super(channel);
        this.loginRequest = loginRequest;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }
}
