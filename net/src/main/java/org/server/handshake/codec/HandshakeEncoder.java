package org.server.handshake.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.handshake.HandshakeHandler;
import org.server.handshake.InboundHandshakePacket;
import org.server.login.codec.LoginDecoder;
import org.server.login.codec.LoginEncoder;
import org.server.login.LoginHandler;

import java.security.SecureRandom;

public class HandshakeEncoder extends MessageToByteEncoder<InboundHandshakePacket> {

    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, InboundHandshakePacket gamePacket, ByteBuf byteBuf) {
        if(gamePacket.magicNumber() != 14) {
            //do something
            return;
        }

        byteBuf.alloc().buffer(17);
        byteBuf.writeLong(0);
        byteBuf.writeByte(0);
        byteBuf.writeLong(RANDOM.nextLong());

        channelHandlerContext.pipeline().replace(HandshakeDecoder.class.getSimpleName(), LoginDecoder.class.getSimpleName(), new LoginDecoder());
        channelHandlerContext.pipeline().replace(HandshakeEncoder.class.getSimpleName(), LoginEncoder.class.getSimpleName(), new LoginEncoder());
        channelHandlerContext.pipeline().replace(HandshakeHandler.class.getSimpleName(), LoginHandler.class.getSimpleName(), new LoginHandler());
    }
}
