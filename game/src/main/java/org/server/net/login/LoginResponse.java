package org.server.net.login;

public record LoginResponse(
        byte responseCode,
        byte playerStatus,
        byte flagged
) {
}
