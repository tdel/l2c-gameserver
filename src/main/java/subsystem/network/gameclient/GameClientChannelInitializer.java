package subsystem.network.gameclient;

import app.kernel.Kernel;
import subsystem.network.gameclient.packets.codec.CryptCodec;
import subsystem.network.gameclient.packets.codec.LengthFieldBasedFrameEncoder;
import subsystem.network.gameclient.packets.codec.PacketEncoder;
import subsystem.network.gameclient.packets.security.BlowfishKeygen;
import subsystem.network.gameclient.packets.security.Crypt;
import com.google.inject.Inject;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

public class GameClientChannelInitializer extends ChannelInitializer<SocketChannel>
{
    private final Kernel kernel;

    private final LengthFieldBasedFrameEncoder frameEncoder;

    private final PacketEncoder packetEncoder;

    @Inject
    public GameClientChannelInitializer(Kernel _kernel) {
        this.kernel = _kernel;
        this.frameEncoder = new LengthFieldBasedFrameEncoder();
        this.packetEncoder = new PacketEncoder(ByteOrder.LITTLE_ENDIAN, 0x8000 - 2);
    }

    @Override
    protected void initChannel(SocketChannel ch) {

        Crypt crypt = new Crypt();
        ch.pipeline().addLast("length-decoder",new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 0x8000 - 2, 0, 2, -2, 2, false));
        ch.pipeline().addLast("length-encoder", this.frameEncoder);
        ch.pipeline().addLast("crypt-codec", new CryptCodec(crypt));
        ch.pipeline().addLast("packet-encoder", this.packetEncoder);
        ch.pipeline().addLast("handler", new ChannelHandler(this.kernel, crypt, this.kernel.getService(BlowfishKeygen.class)));
    }
}
