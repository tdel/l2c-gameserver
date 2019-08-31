package network.loginserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class LoginServerChannelInitializer extends ChannelInitializer<SocketChannel>
{

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast("decoder", new StringDecoder());
        ch.pipeline().addLast("encoder", new StringEncoder());
        ch.pipeline().addLast("handler", new LoginServerChannelHandler());
    }
}
