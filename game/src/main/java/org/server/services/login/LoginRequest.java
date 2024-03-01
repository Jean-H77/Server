package org.server.services.login;

public record LoginRequest(
        String username,
        String password
) {
}
