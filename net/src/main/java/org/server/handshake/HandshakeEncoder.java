package org.server.handshake;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.security.SecureRandom;

public class HandshakeEncoder extends MessageToByteEncoder<InboundHandshakePacket> {

    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, InboundHandshakePacket gamePacket, ByteBuf byteBuf) {
        if(gamePacket.magicNumber() != 14) {
            //do something
            return;
        }

        ByteBuf buf = channelHandlerContext.alloc().buffer(17);
        buf.writeLong(0);
        buf.writeByte(0);
        long sessionKey = RANDOM.nextLong();
        buf.writeLong(sessionKey);
        System.out.println(sessionKey);
        channelHandlerContext.write(buf);
    }
}
