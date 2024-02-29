package org.server.bootstrap.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.server.handshake.codec.HandshakeDecoder;
import org.server.handshake.codec.HandshakeEncoder;
import org.server.handshake.HandshakeHandler;

public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline p = socketChannel.pipeline();

        p
                .addLast(HandshakeDecoder.class.getSimpleName(), new HandshakeDecoder())
                .addLast(HandshakeEncoder.class.getSimpleName(), new HandshakeEncoder())
                .addLast(HandshakeHandler.class.getSimpleName(), new HandshakeHandler());
    }
}
