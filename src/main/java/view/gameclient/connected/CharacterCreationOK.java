package view.gameclient.connected;

import app.kernel.subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketWriter;

public class CharacterCreationOK implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeD(0x0F);

        _writer.writeD(0x01);
    }
}
