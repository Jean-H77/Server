package org.server.services.handshake;

public record HandshakeResponse(
        byte responseCode,
        long serverSessionKey
) {
}
