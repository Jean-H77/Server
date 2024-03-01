package org.server.engine;

import com.google.inject.Inject;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.server.services.ServiceManager;
import org.server.services.handshake.HandshakeRequest;
import org.server.services.login.LoginRequest;
import org.server.session.Session;
import org.server.session.impl.HandshakeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
public class GameHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameHandler.class);
    private final ServiceManager serviceManager;

    @Inject
    public GameHandler(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

     public static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("session");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof HandshakeRequest r) {
            serviceManager.handshakeService().verify(new HandshakeSession(ctx.channel(), r));
        }

        if(msg instanceof LoginRequest lr) {
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (!e.getMessage().contains("An existing connection was forcibly closed by the remote host")) {
            LOGGER.warn("An Exception has occurred for channel: " + ctx.channel() + ", closing...", e);
        }
        ctx.channel().close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        /*Channel channel = ctx.channel();
        Session session = channel.attr(ApolloHandler.SESSION_KEY).getAndRemove();
        if (session != null) {
            session.destroy();
        }
        logger.fine("Channel disconnected: " + channel);
        channel.close();*/
    }
}
