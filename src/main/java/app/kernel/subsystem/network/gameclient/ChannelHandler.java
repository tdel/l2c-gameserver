package app.kernel.subsystem.network.gameclient;

import app.kernel.Kernel;
import app.kernel.subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketReader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.gameclient.connected.ShowCharactersSelection;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;


public class ChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger();

    private Kernel kernel;
    private Channel channel;

    private GameClientConnectionState connectionState;

    public ChannelHandler(Kernel _kernel) {
        this.kernel = _kernel;
    }

    public void setConnectionState(GameClientConnectionState _state ) {
        this.connectionState = _state;
    }

    public void sendPacket(OutgoingGameClientPacketInterface _packet) {
        this.channel.writeAndFlush(_packet);
    }

    public void disconnect() {
        this.channel.disconnect();
    }

    @Override
    public void channelActive(ChannelHandlerContext _ctx) throws Exception {
        super.channelActive(_ctx);

        this.channel = _ctx.channel();
        InetSocketAddress address = (InetSocketAddress) _ctx.channel().remoteAddress();
        this.connectionState = GameClientConnectionState.CONNECTED;

        logger.info("New client <" + address.getHostString() + ">");

        this.sendPacket(new ShowCharactersSelection());
    }



    @Override
    public void channelRead(ChannelHandlerContext _ctx, Object _msg) throws Exception {

        ByteBuf in = this.readByteBuf(_msg);
        if (null == in) {
            return;
        }

        final int packetId = in.readUnsignedByte() & 0xFF;

        IncomingGameClientPacketInterface packet = null;

        if (null == packet) {
            logger.error("Packet not found : 0x" + String.format("%02X", packetId) + " - state " + this.connectionState);
            in.readerIndex(in.writerIndex());

            return;
        }

        logger.info("Executing packet 0x" + String.format("%02X", packetId) + " <" + packet.getClass().getName() + ">");

        try {
            packet.execute(new PacketReader(in), this);
        } finally {
            // We always consider that we read whole packet.
            in.readerIndex(in.writerIndex());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext _ctx, Throwable _cause) {
        _cause.printStackTrace();
    }

    private ByteBuf readByteBuf(Object _msg) {
        ByteBuf in = (ByteBuf) _msg;

        if ((in == null) || !in.isReadable()) {
            return null;
        }

        if (in.order() != ByteOrder.LITTLE_ENDIAN) {
            in = in.order(ByteOrder.LITTLE_ENDIAN);
        }

        return in;
    }
}