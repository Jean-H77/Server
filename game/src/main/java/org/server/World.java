package org.server;

import org.server.services.login.LoginRequest;
import org.server.net.login.LoginResponse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class World {

    private final BlockingQueue<LoginRequest> LOGIN_REQUEST_LIST = new LinkedBlockingQueue<>();

    public void addToLoginQueue(LoginRequest loginRequest) {
        LOGIN_REQUEST_LIST.add(loginRequest);
    }

    public void handleLoginQueue() {
        if(LOGIN_REQUEST_LIST.isEmpty()) return;

        LoginRequest lr = LOGIN_REQUEST_LIST.poll();

        System.out.println("Got: " + lr.username());
        lr.channel().writeAndFlush(new LoginResponse((byte) 2, (byte) 0, (byte) 0));
    }
}
