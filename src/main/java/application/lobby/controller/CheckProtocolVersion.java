package application.lobby.controller;

import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.ProtocolVersionAndKey;

public class CheckProtocolVersion implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        int version = _reader.readD();

        int serverId = 1;
        _client.sendPacket(new ProtocolVersionAndKey(true, _client.activateCrypt(), serverId));

    }

}
