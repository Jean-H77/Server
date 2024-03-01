package org.server.services.handshake;

public record HandshakeRequest(
        short magicNumber,
        short nameHash
) {
}
