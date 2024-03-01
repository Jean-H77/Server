package org.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.server.net.NetworkModule;
import org.server.engine.GameEngine;
import org.server.engine.GameEngineModule;
import org.server.services.ServiceModule;

public class ServerLauncher {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new GameEngineModule(),
                new NetworkModule(),
                new ServiceModule());

        GameEngine gameEngine = injector.getInstance(GameEngine.class);
        gameEngine.start(injector);
    }
}
