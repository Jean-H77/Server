package org.server.net.handshake.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.server.services.handshake.HandshakeRequest;

import java.util.List;

public class HandshakeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        short magicNumber = byteBuf.readUnsignedByte();
        short nameHash = byteBuf.readUnsignedByte();
        HandshakeRequest hsp = new HandshakeRequest(magicNumber, nameHash);
        list.add(hsp);
    }
}
