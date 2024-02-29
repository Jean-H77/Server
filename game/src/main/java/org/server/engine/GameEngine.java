package org.server.engine;

import org.server.bootstrap.Bootstrap;
import org.server.bootstrap.initializer.ChannelInit;
import org.server.bootstrap.impl.NioNetworkBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Todo fix gradle multi-module dependencies
public enum GameEngine {
    INSTANCE(new NioNetworkBootstrap(43594, new ChannelInit()));

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final Bootstrap bootstrap;

    GameEngine(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public static void main(String[] args) {
        try(ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1)) {
            executor.scheduleAtFixedRate(new GameLoop(), 0, 600, TimeUnit.MILLISECONDS);
            INSTANCE.bootstrap.run();
        } catch (Exception e) {
            LOGGER.error("Error with the game engine", e);
        }
    }
}