package controller.gameclient.connected;

import subsystem.network.gameclient.ChannelHandler;
import subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import subsystem.network.gameclient.packets.PacketReader;
import view.gameclient.connected.ShowAllCharacters;

public class RequestGoToCharacterSelection implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, ChannelHandler _client) {
        _client.sendPacket(new ShowAllCharacters());
    }
}
