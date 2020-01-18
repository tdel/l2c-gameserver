package network.gameclient;

import controller.GameClientController;
import model.instance.PlayerInstance;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import network.gameclient.packets.security.Crypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;


public class GameClientChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger();

    private final GameClientController controller;
    private final Crypt crypt;

    private Channel channel;
    private GameClientConnectionState connectionState;

    private String login;
    private PlayerInstance player;


    public GameClientChannelHandler(GameClientController _gameClientController, Crypt _crypt) {
        this.controller = _gameClientController;
        this.crypt = _crypt;
    }

    public void setPlayerInstance(PlayerInstance _player) {
        this.player = _player;
    }
    public PlayerInstance getPlayer() {
        return this.player;
    }
    public String getPlayerName() {
        if (null == this.player) {
            return "N/A";
        }

        return this.player.getName();
    }

    public void setLogin(String _login) {
        this.login = _login;
    }

    public String getLogin() {
        return this.login;
    }

    public byte[] activateCrypt() {
        return this.crypt.generateKey();
    }

    public void setConnectionState(GameClientConnectionState _state ) {
        this.connectionState = _state;
    }

    public GameClientConnectionState getState() {
        return this.connectionState;
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
    }

    @Override
    public void channelRead(ChannelHandlerContext _ctx, Object _msg) throws Exception {

        ByteBuf in = this.readByteBuf(_msg);
        if (null == in) {
            return;
        }

        PacketReader reader = new PacketReader(in);

        try {
            IncomingGameClientPacketInterface packet = this.controller.handle(reader, this);
            if (packet == null) {
                return;
            }

            packet.execute(reader, this);
        } finally {
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