package org.server.net.handshake.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.services.handshake.HandshakeResponse;

public class HandshakeEncoder extends MessageToByteEncoder<HandshakeResponse> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, HandshakeResponse handshakeResponse, ByteBuf byteBuf) {
        byteBuf.alloc().buffer(17);
        byteBuf.writeLong(0);
        byteBuf.writeByte(handshakeResponse.responseCode());
        byteBuf.writeLong(handshakeResponse.serverSessionKey());
    }
}
