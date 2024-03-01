package org.server.engine;

import com.google.inject.Inject;
import org.server.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop implements Runnable {

    public static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);
    public static final int TICK_DELAY = 600;
    private final World world;

    private long lastTickNano;

    @Inject
    public GameLoop(World world) {
        this.world = world;
    }

    public void start() {
        try(ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1)) {
            executorService.scheduleAtFixedRate(this, 0, TICK_DELAY, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.error("An Exception has occurred while starting the game loop", e);
        }
    }

    @Override
    public void run() {
        long tickTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - lastTickNano);
        lastTickNano = System.nanoTime();
        LOGGER.info("Tick took: " + tickTime);
    }

}
