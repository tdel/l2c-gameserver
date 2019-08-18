package controller.gameclient.connected;

import app.kernel.subsystem.network.gameclient.ChannelHandler;
import app.kernel.subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketReader;
import view.gameclient.connected.CharactersSelectionList;

public class RequestNewCharacter implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, ChannelHandler _client) {
        _client.sendPacket(new CharactersSelectionList());
        _client.sendPacket(new CharactersSelectionList());
    }

}
