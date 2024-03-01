package org.server.net.bootstrap;

import io.netty.bootstrap.ServerBootstrap;

public interface Bootstrap {
    void run() throws Exception;
    ServerBootstrap get();
}
