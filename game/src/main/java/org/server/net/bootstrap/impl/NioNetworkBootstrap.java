package org.server.net.bootstrap.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.server.net.bootstrap.Bootstrap;

public class NioNetworkBootstrap implements Bootstrap {

    private final int port;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final ChannelHandler handler;
    private final ServerBootstrap bootstrap = new ServerBootstrap();

    @Inject
    public NioNetworkBootstrap(@Named("Port") int port,
                               @Named("Nio") EventLoopGroup bossGroup,
                               @Named("Nio") EventLoopGroup workerGroup, ChannelHandler handler) {
        this.port = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.handler = handler;
    }

    @Override
    public void run() throws Exception {
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(handler)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);

            ChannelFuture f = bootstrap.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public ServerBootstrap get() {
        return bootstrap;
    }
}
