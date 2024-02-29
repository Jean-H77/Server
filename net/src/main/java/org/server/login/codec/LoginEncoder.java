package org.server.login.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.login.LoginResponsePacket;

public class LoginEncoder extends MessageToByteEncoder<LoginResponsePacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket, ByteBuf byteBuf) throws Exception {
        byteBuf.alloc().buffer(3);
        byteBuf
                .writeByte(loginResponsePacket.responseCode())
                .writeByte(loginResponsePacket.playerStatus())
                .writeByte(loginResponsePacket.flagged());
    }
}
