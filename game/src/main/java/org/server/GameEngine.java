package org.server;

import org.server.bootstrap.Bootstrap;
import org.server.bootstrap.initializer.ChannelInit;
import org.server.bootstrap.impl.NioNetworkBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Todo fix gradle multi-module dependencies
public enum GameEngine {
    INSTANCE(new NioNetworkBootstrap(43594, new ChannelInit()));

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final Bootstrap bootstrap;

    GameEngine(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public static void main(String[] args) throws Exception {
        LOGGER.info("STARTING SERVER");
        INSTANCE.bootstrap.run();
    }
}