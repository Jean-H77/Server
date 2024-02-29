package org.server.login;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class LoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof LoginResponsePacket) {
            System.out.println("Writing");
            ctx.write(msg);
        }
        ctx.flush();
        ReferenceCountUtil.release(msg);
    }
}
