package org.server.net.gamepacket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.net.gamepacket.GamePacket;

public class GamePacketEncoder extends MessageToByteEncoder<GamePacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, GamePacket gamePacket, ByteBuf byteBuf) throws Exception {

    }
}
