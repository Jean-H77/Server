package org.server.services;

import org.server.session.Session;

public abstract class Service {
    protected abstract void process(Session session);
}
