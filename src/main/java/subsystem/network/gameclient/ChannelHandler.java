package subsystem.network.gameclient;

import app.kernel.Kernel;
import subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import subsystem.network.gameclient.packets.PacketReader;
import subsystem.network.gameclient.packets.security.BlowfishKeygen;
import subsystem.network.gameclient.packets.security.Crypt;
import controller.gameclient.connected.CheckProtocolVersion;
import controller.gameclient.connected.RequestGoToCharacterSelection;
import controller.gameclient.connected.RequestNewCharacter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.gameclient.connected.ShowAllCharacters;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;


public class ChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger();

    private Kernel kernel;
    private Channel channel;

    private Crypt crypt;
    private BlowfishKeygen blowfishKeygen;

    private GameClientConnectionState connectionState;

    public ChannelHandler(Kernel _kernel, Crypt _crypt, BlowfishKeygen _blowfishKeygen) {
        this.kernel = _kernel;
        this.crypt = _crypt;
        this.blowfishKeygen = _blowfishKeygen;
    }

    public byte[] activateCrypt() {
        byte[] key = this.blowfishKeygen.getRandomKey();
        this.crypt.setKey(key);

        return key;
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

        this.sendPacket(new ShowAllCharacters());
    }



    @Override
    public void channelRead(ChannelHandlerContext _ctx, Object _msg) throws Exception {

        ByteBuf in = this.readByteBuf(_msg);
        if (null == in) {
            return;
        }

        final int packetId = in.readUnsignedByte() & 0xFF;
        int packetId2 = -1;

        IncomingGameClientPacketInterface packet = null;
        switch (packetId) {
            case 0x0e:
                packet = this.kernel.getService(CheckProtocolVersion.class);
                break;
            case 0x13:
                packet = this.kernel.getService(RequestNewCharacter.class);
                break;
            case 0x0c:
                packet = this.kernel.getService(RequestNewCharacter.class);
                break;
            case 0xd0: // EX packet !
                if (in.readableBytes() < 2) {
                    // problem
                    logger.error("2nd OPCode not present : 0x" + String.format("%02X", packetId) + " - state " + this.connectionState);

                    break;
                }

                packetId2 = in.readShortLE() & 0xff;

                logger.info("2nd OPCode found :  0x" + String.format("%02X", packetId2) );

                switch (packetId2) {
                    case 0x36:
                        packet = this.kernel.getService(RequestGoToCharacterSelection.class);
                        break;
                }

                if (packetId2 == -1) {
                    logger.error("Packet not found : 0x" + String.format("%02X", packetId) + " [0x" + String.format("%02X", packetId2) + "] - state " + this.connectionState);
                }

                break;

        }

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