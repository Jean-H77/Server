package org.server;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.server.World;
import org.server.engine.GameEngine;
import org.server.engine.GameLoop;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameLoop.class).in(Scopes.SINGLETON);
        bind(GameEngine.class).asEagerSingleton();
        bind(World.class).in(Scopes.SINGLETON);
        bind(ScheduledExecutorService.class).toInstance(Executors.newScheduledThreadPool(1));
    }
}
