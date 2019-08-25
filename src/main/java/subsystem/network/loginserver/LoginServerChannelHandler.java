package subsystem.network.loginserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginServerChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger();

    private Channel channel;


    public LoginServerChannelHandler() {

    }

    public void sendPacket(String _packet) {
        logger.info("Sending <" + _packet + ">");

        this.channel.writeAndFlush(_packet);
    }

    @Override
    public void channelActive(ChannelHandlerContext _ctx) throws Exception {
        super.channelActive(_ctx);

        this.channel = _ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext _ctx, Object _msg) {

        System.out.println(_msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext _ctx, Throwable _cause) {
        _cause.printStackTrace();
    }

}