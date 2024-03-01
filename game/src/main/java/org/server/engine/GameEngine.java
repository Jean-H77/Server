package org.server.engine;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.server.net.bootstrap.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;

public class GameEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);
    private final Bootstrap bootstrap;
    private final ScheduledExecutorService executorService;

    @Inject
    public GameEngine(@Named("Nio") Bootstrap bootstrap, ScheduledExecutorService executorService) {
        this.bootstrap = bootstrap;
        this.executorService = executorService;
    }

    public void start() {
        try {
            bootstrap.run();
        } catch (Exception e) {
            LOGGER.error("Error while bootstrapping", e);
        }
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }
}