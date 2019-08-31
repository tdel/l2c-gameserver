package controller.gameclient.connected;

import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.connected.ProtocolVersionAndKey;

public class CheckProtocolVersion implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        int version = _reader.readD();

        int serverId = 1;
        _client.sendPacket(new ProtocolVersionAndKey(true, _client.activateCrypt(), serverId));

    }

}
