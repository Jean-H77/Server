package org.server.engine;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.server.bootstrap.Bootstrap;
import org.server.bootstrap.initializer.ChannelInit;
import org.server.bootstrap.impl.NioNetworkBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Todo fix gradle multi-module dependencies
public class GameEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final Bootstrap bootstrap;
    private final GameLoop gameLoop;

    @Inject
    public GameEngine(@Named("Nio") Bootstrap bootstrap,
                      GameLoop gameLoop) {
        this.bootstrap = bootstrap;
        this.gameLoop = gameLoop;
    }

    public void start() {
        try {
            LOGGER.info("Starting");
            Thread.startVirtualThread(gameLoop);
            bootstrap.run();
        } catch (Exception e) {
            LOGGER.error("Error while bootstrapping", e);
        }
    }
}