package org.server.services;

import com.google.inject.Inject;
import org.server.services.handshake.HandshakeService;
import org.server.services.login.LoginService;

public record ServiceManager(
        HandshakeService handshakeService,
        LoginService loginService
) {

    @Inject
    public ServiceManager {
    }
}
