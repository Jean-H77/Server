package org.server.services.login;

import com.google.inject.Inject;
import io.netty.channel.Channel;
import org.server.World;
import org.server.entity.Player;
import org.server.net.gamepacket.codec.GamePacketDecoder;
import org.server.net.gamepacket.codec.GamePacketEncoder;
import org.server.net.login.codec.LoginDecoder;
import org.server.net.login.codec.LoginEncoder;
import org.server.services.Service;
import org.server.session.Session;
import org.server.session.impl.LoginSession;
import org.server.session.impl.PlayerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoginService extends Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final BlockingQueue<LoginSession> loginSessions = new LinkedBlockingQueue<>();
    private final World world;

    @Inject
    public LoginService(World world) {
        this.world = world;
    }

    @Override
    public void process(Session session) {
        if (session instanceof LoginSession ls) {
            addToQueue(ls);
        }
    }

    public void addToQueue(LoginSession session) {
        boolean result = loginSessions.offer(session);
        LOGGER.info(result ? "session added to login queue" : " session could not be added to login queue");
    }

    public void start() {
        Thread.startVirtualThread(new LoginWorker());
        LOGGER.info("Started LoginService");
    }

    class LoginWorker implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    LoginSession loginSession = loginSessions.take();
                    LoginRequest request = loginSession.getLoginRequest();
                    Channel channel = loginSession.getChannel();

                    LoginResponse loginResponse = new LoginResponse((byte) 2, (byte) 0, (byte) 0);
                    channel.writeAndFlush(loginResponse);

                    channel.pipeline().replace(LoginDecoder.class.getSimpleName(), GamePacketDecoder.class.getSimpleName(), new GamePacketDecoder());
                    channel.pipeline().replace(LoginEncoder.class.getSimpleName(), GamePacketEncoder.class.getSimpleName(), new GamePacketEncoder());

                    Player player = new Player(request.username(), request.password());
                    PlayerSession playerSession = new PlayerSession(channel, player);

                    world.addPlayer(player);

                } catch (InterruptedException e) {
                    LOGGER.error("An Exception has occurred in the LoginWorker loop", e);
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
