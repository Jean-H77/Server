package org.server.engine;

import com.google.inject.Inject;
import org.server.World;

public class GameLoop implements Runnable {

    public static final int TICK_DELAY = 600;
    private final World world;

    public boolean isRunning = true;

    @Inject
    public GameLoop(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                Thread.sleep(TICK_DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopGameLoop() {
        isRunning = false;
    }
}
