package org.server.engine;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import io.netty.channel.ChannelInboundHandler;
import org.server.net.bootstrap.Bootstrap;
import org.server.net.bootstrap.impl.NioNetworkBootstrap;
import org.server.World;

public class GameEngineModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameLoop.class).in(Scopes.SINGLETON);
        bind(GameEngine.class).asEagerSingleton();
        bind(World.class).in(Scopes.SINGLETON);
    }
}
