package org.server.handshake;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HandshakeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        short magicNumber = byteBuf.readUnsignedByte();
        short nameHash = byteBuf.readUnsignedByte();
        InboundHandshakePacket hsp = new InboundHandshakePacket(magicNumber, nameHash);
        list.add(hsp);
    }
}
