package org.server.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class BootstrapModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventLoopGroup.class)
                .annotatedWith(Names.named("Nio"))
                .to(NioEventLoopGroup.class);

        bindConstant()
                .annotatedWith(Names.named("Port"))
                .to(43594);
    }
}
