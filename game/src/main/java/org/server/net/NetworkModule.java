package org.server.net;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.server.engine.GameHandler;
import org.server.net.bootstrap.Bootstrap;
import org.server.net.bootstrap.impl.NioNetworkBootstrap;
import org.server.net.bootstrap.initializer.ChannelInit;
import org.server.net.login.codec.LoginDecoder;
import org.server.net.login.codec.LoginEncoder;
import org.server.net.handshake.codec.HandshakeDecoder;
import org.server.net.handshake.codec.HandshakeEncoder;


public class NetworkModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventLoopGroup.class)
                .annotatedWith(Names.named("Nio"))
                .to(NioEventLoopGroup.class);

        bindConstant()
                .annotatedWith(Names.named("Port"))
                .to(43594);


        bind(Bootstrap.class)
                .annotatedWith(Names.named("Nio"))
                .to(NioNetworkBootstrap.class);

        bind(ChannelHandler.class).to(ChannelInit.class).asEagerSingleton();
        bind(ChannelInboundHandler.class).to(GameHandler.class).in(Scopes.SINGLETON);
        bind(HandshakeDecoder.class).in(Scopes.SINGLETON);
        bind(HandshakeEncoder.class).in(Scopes.SINGLETON);
        bind(LoginDecoder.class).in(Scopes.SINGLETON);
        bind(LoginEncoder.class).in(Scopes.SINGLETON);
    }
}
