package org.server.login.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.server.login.LoginResponsePacket;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.server.gamepacket.GamePacketBuffer.STRING_TERMINATOR;

public class LoginDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte connectedStatus = byteBuf.readByte();
        byte size = byteBuf.readByte();
        byte is255 = byteBuf.readByte();
        short is317 = byteBuf.readShort();
        byte clientVersion = byteBuf.readByte();

        int[] crcs = new int[9];
        for(int i = 0; i < crcs.length; i++) crcs[i] = byteBuf.readInt(); //we'll ignore crcs for now

        byte size2 = byteBuf.readByte();
        byte is10 = byteBuf.readByte();
        long clientSessionKey = byteBuf.readLong();
        long serverSessionKey = byteBuf.readLong();
        int userId = byteBuf.readInt();
        String username = readRString(byteBuf);
        String password = readRString(byteBuf);

        System.out.println(
                  "Connected Status: " + connectedStatus
                + "\nsize: " + size
                + "\nis255: " + is255
                + "\nis317: " + is317
                + "\nclientVersion: " + clientVersion
                + "\nsize2: " + size2
                + "\nis10: " + is10
                + "\nclient session key: " + clientSessionKey
                + "\nserver session key: " + serverSessionKey
                + "\nuserId: " + userId
                + "\nusername: " + username
                + "\npassword: " + password
        );

        // we'll just send response code 2
        list.add(new LoginResponsePacket((byte) 2, (byte) 0, (byte) 0));
    }

    private static String readRString(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        byte b;
        int i = 0;
        while((b = buf.readByte()) != STRING_TERMINATOR) {
            bytes[i] = b;
            i++;
        }
        return new String(Arrays.copyOfRange(bytes, 0, i), StandardCharsets.UTF_8);
    }
}
