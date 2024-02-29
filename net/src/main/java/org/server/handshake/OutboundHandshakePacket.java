package org.server.handshake;

import io.netty.buffer.ByteBuf;

public record OutboundHandshakePacket(ByteBuf buf) {
}
