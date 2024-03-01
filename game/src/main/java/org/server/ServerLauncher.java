package org.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.server.engine.GameLoop;
import org.server.net.NetworkModule;
import org.server.engine.GameEngine;
import org.server.services.ServiceManager;
import org.server.services.ServiceModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerLauncher.class);

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new GameModule(),
                new NetworkModule(),
                new ServiceModule());

        LOGGER.info("Starting services");
        ServiceManager serviceManager = injector.getInstance(ServiceManager.class);
        serviceManager.startServices();

        LOGGER.info("Starting Game Loop");
        GameLoop gameLoop = injector.getInstance(GameLoop.class);
        gameLoop.start();

        LOGGER.info("Starting Game Engine");
        GameEngine gameEngine = injector.getInstance(GameEngine.class);
        gameEngine.start();
    }
}
