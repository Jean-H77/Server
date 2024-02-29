package org.server.handshake;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.server.login.LoginDecoder;
import org.server.login.LoginEncoder;
import org.server.login.LoginHandler;

public class HandshakeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if(msg instanceof InboundHandshakePacket) {
            ctx.write(msg);
        } else if(msg instanceof OutboundHandshakePacket out) {
            ctx.write(out.buf());
            ctx.pipeline().replace(HandshakeDecoder.class.getSimpleName(), LoginDecoder.class.getSimpleName(), new LoginDecoder());
            ctx.pipeline().replace(HandshakeEncoder.class.getSimpleName(), LoginEncoder.class.getSimpleName(), new LoginEncoder());
            ctx.pipeline().replace(HandshakeHandler.class.getSimpleName(), LoginHandler.class.getSimpleName(), new LoginHandler());
        }

        ctx.flush();
        ReferenceCountUtil.release(msg);
    }
}
