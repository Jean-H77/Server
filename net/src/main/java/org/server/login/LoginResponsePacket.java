package org.server.login;

public record LoginResponsePacket(
        byte responseCode,
        byte playerStatus,
        byte flagged
) {
}
