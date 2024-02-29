package org.server.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.gamepacket.GamePacket;

public class LoginEncoder extends MessageToByteEncoder<GamePacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, GamePacket gamePacket, ByteBuf byteBuf) throws Exception {

    }
}
