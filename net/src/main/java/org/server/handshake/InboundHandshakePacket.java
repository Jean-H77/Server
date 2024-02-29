package org.server.handshake;

public record InboundHandshakePacket(
        short magicNumber,
        short nameHash
) {
}
