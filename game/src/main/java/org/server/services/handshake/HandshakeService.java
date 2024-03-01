package org.server.services.handshake;

import io.netty.channel.Channel;
import org.server.net.handshake.codec.HandshakeDecoder;
import org.server.net.handshake.codec.HandshakeEncoder;
import org.server.net.login.codec.LoginDecoder;
import org.server.net.login.codec.LoginEncoder;
import org.server.services.Service;
import org.server.session.impl.HandshakeSession;

import java.security.SecureRandom;

//@todo do response codes
public class HandshakeService extends Service {

    private static final SecureRandom RANDOM = new SecureRandom();

    public void verify(HandshakeSession hs) {
        HandshakeRequest request = hs.getRequest();
        Channel channel = hs.getChannel();

        if(request.magicNumber() != 14) {
            return;
        }

        long seed = RANDOM.nextLong();
        HandshakeResponse handshakeResponse = new HandshakeResponse((byte) 0, seed);
        channel.writeAndFlush(handshakeResponse);

        channel.pipeline().replace(HandshakeDecoder.class.getSimpleName(), LoginDecoder.class.getSimpleName(), new LoginDecoder());
        channel.pipeline().replace(HandshakeEncoder.class.getSimpleName(), LoginEncoder.class.getSimpleName(), new LoginEncoder());
    }
}
