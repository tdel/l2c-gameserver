package network.loginserver;

import com.google.inject.Inject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginServerClient {

    private static final Logger logger = LogManager.getLogger();

    private EventLoopGroup workerGroup;

    private ChannelInitializer channelInitializer;

    private ChannelFuture channel;

    @Inject
    public LoginServerClient(LoginServerChannelInitializer _channelInitializer) {
        this.channelInitializer = _channelInitializer;
    }

    public void start(String _host, int _port) {

        logger.info("Starting connection to " + _host + ":" + _port);

        this.workerGroup = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap(); // (1)
        b.group(workerGroup); // (2)

        b.channel(NioSocketChannel.class); // (3)
        b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
        b.handler(this.channelInitializer);

        try {
            this.channel = b.connect(_host, _port).sync(); // (5)
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            return;
        }

        logger.info("Connection established");
    }
}
