package org.server.services;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.server.services.handshake.HandshakeService;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServiceManager.class).in(Scopes.SINGLETON);
        bind(HandshakeService.class);
    }
}
