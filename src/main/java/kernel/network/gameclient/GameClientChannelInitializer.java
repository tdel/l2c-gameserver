package kernel.network.gameclient;

import application.core.controller.GameClientController;
import kernel.network.gameclient.packets.codec.CryptCodec;
import kernel.network.gameclient.packets.codec.LengthFieldBasedFrameEncoder;
import kernel.network.gameclient.packets.codec.PacketEncoder;
import kernel.network.gameclient.packets.security.BlowfishKeygen;
import kernel.network.gameclient.packets.security.Crypt;
import com.google.inject.Inject;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

public class GameClientChannelInitializer extends ChannelInitializer<SocketChannel>
{
    private final LengthFieldBasedFrameEncoder frameEncoder;
    private final PacketEncoder packetEncoder;
    private final GameClientController controller;

    private final BlowfishKeygen blowfishKeygen;

    @Inject
    public GameClientChannelInitializer(GameClientController _controller, BlowfishKeygen _blowfishKeygen) {
        this.controller = _controller;
        this.frameEncoder = new LengthFieldBasedFrameEncoder();
        this.packetEncoder = new PacketEncoder(ByteOrder.LITTLE_ENDIAN, 0x8000 - 2);
        this.blowfishKeygen = _blowfishKeygen;
    }

    @Override
    protected void initChannel(SocketChannel ch) {

        Crypt crypt = new Crypt(this.blowfishKeygen);
        ch.pipeline().addLast("length-decoder",new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 0x8000 - 2, 0, 2, -2, 2, false));
        ch.pipeline().addLast("length-encoder", this.frameEncoder);
        ch.pipeline().addLast("crypt-codec", new CryptCodec(crypt));
        ch.pipeline().addLast("packet-encoder", this.packetEncoder);
        ch.pipeline().addLast("handler", new GameClientChannelHandler(this.controller, crypt));
    }
}
