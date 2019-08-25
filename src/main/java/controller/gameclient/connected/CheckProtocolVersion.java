package controller.gameclient.connected;

import subsystem.network.gameclient.ChannelHandler;
import subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import subsystem.network.gameclient.packets.PacketReader;

public class CheckProtocolVersion implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, ChannelHandler _client) {

        int version = _reader.readD();

        int serverId = 1;
        //_client.sendPacket(new ProtocolVersionAndKey(true, _client.activateCrypt(), serverId));

    }

}
