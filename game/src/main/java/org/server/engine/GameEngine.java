package org.server.engine;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.server.net.bootstrap.Bootstrap;
import org.server.services.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final Bootstrap bootstrap;
    private final GameLoop gameLoop;
    private final ServiceManager serviceManager;

    @Inject
    public GameEngine(@Named("Nio") Bootstrap bootstrap,
                      GameLoop gameLoop,
                      ServiceManager serviceManager) {
        this.bootstrap = bootstrap;
        this.gameLoop = gameLoop;
        this.serviceManager = serviceManager;
    }

    public void start(Injector injector) {
        try {
            LOGGER.info("Starting");
            gameLoop.start();
            serviceManager.startServices();
            bootstrap.run();
        } catch (Exception e) {
            LOGGER.error("Error while bootstrapping", e);
        }
    }
}