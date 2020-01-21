package application.lobby.response;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class CharacterCreationOK implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x0F);

        _writer.writeD(0x01);
    }
}
