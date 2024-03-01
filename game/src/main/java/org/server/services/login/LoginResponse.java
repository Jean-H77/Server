package org.server.services.login;

public record LoginResponse(
        byte responseCode,
        byte playerStatus,
        byte flagged
) {
}
