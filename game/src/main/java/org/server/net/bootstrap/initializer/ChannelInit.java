package org.server.net.bootstrap.initializer;

import com.google.inject.Inject;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.server.net.handshake.codec.HandshakeDecoder;
import org.server.net.handshake.codec.HandshakeEncoder;

public class ChannelInit extends ChannelInitializer<SocketChannel> {

    private final ChannelInboundHandler handler;

    @Inject
    public ChannelInit(ChannelInboundHandler handler) {
        this.handler = handler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline p = socketChannel.pipeline();

        p
                .addLast(HandshakeDecoder.class.getSimpleName(), new HandshakeDecoder())
                .addLast(HandshakeEncoder.class.getSimpleName(), new HandshakeEncoder())
                .addLast(handler.getClass().getSimpleName(), handler);
    }
}
