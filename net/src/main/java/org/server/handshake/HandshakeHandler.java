package org.server.handshake;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class HandshakeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof InboundHandshakePacket) {
            ctx.write(msg);
        }
        ctx.flush();
        ReferenceCountUtil.release(msg);
    }
}
