package org.server.services;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.server.services.handshake.HandshakeService;
import org.server.services.login.LoginService;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServiceManager.class).in(Scopes.SINGLETON);
        bind(HandshakeService.class).in(Scopes.SINGLETON);
        bind(LoginService.class).in(Scopes.SINGLETON);
    }
}
