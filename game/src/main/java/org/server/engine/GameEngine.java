package org.server.engine;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.server.net.bootstrap.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Todo fix gradle multi-module dependencies
public class GameEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final Bootstrap bootstrap;
    private final GameLoop gameLoop;

    @Inject
    public GameEngine(@Named("Nio") Bootstrap bootstrap, GameLoop gameLoop) {
        this.bootstrap = bootstrap;
        this.gameLoop = gameLoop;
    }

    public void start(Injector injector) {
        try {
            LOGGER.info("Starting");
            Thread.startVirtualThread(gameLoop);
            bootstrap.run();
        } catch (Exception e) {
            LOGGER.error("Error while bootstrapping", e);
        }
    }
}