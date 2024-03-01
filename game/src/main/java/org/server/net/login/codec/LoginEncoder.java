package org.server.net.login.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.services.login.LoginResponse;

public class LoginEncoder extends MessageToByteEncoder<LoginResponse> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LoginResponse loginResponse, ByteBuf byteBuf) throws Exception {
        byteBuf.alloc().buffer(3);
        byteBuf
                .writeByte(loginResponse.responseCode())
                .writeByte(loginResponse.playerStatus())
                .writeByte(loginResponse.flagged());
    }
}
