package org.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.server.bootstrap.BootstrapModule;
import org.server.engine.GameEngine;
import org.server.engine.GameModule;

public class ServerLauncher {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new GameModule(),
                new BootstrapModule());

        GameEngine gameEngine = injector.getInstance(GameEngine.class);
        gameEngine.start();
    }
}
