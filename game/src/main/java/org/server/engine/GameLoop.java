package org.server.engine;

public class GameLoop implements Runnable {

    public static final int TICK_DELAY = 600;

    public boolean isRunning = true;

    @Override
    public void run() {
        while(isRunning) {
            System.out.println("Running");

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
