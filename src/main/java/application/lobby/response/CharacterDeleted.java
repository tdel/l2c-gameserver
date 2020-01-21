package application.lobby.response;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class CharacterDeleted implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x1D);
    }
}
