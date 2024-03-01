package org.server.services;

import com.google.inject.Inject;
import org.server.services.handshake.HandshakeService;
import org.server.services.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record ServiceManager(
        HandshakeService handshakeService,
        LoginService loginService
) {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);

    @Inject
    public ServiceManager {
    }

    public void startServices() {
        loginService.start();
    }
}
