package controller.gameclient.connected;

import app.kernel.subsystem.network.gameclient.ChannelHandler;
import app.kernel.subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketReader;
import view.gameclient.connected.ProtocolVersionAndKey;

public class CheckProtocolVersion implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, ChannelHandler _client) {

        int version = _reader.readD();

        int serverId = 1;
        //_client.sendPacket(new ProtocolVersionAndKey(true, _client.activateCrypt(), serverId));

    }

}
