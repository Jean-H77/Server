package org.server.engine;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import org.server.bootstrap.Bootstrap;
import org.server.bootstrap.impl.NioNetworkBootstrap;

public class GameModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameLoop.class).in(Scopes.SINGLETON);
        bind(GameEngine.class).asEagerSingleton();

        bind(Bootstrap.class)
                .annotatedWith(Names.named("Nio"))
                .to(NioNetworkBootstrap.class);
    }
}
