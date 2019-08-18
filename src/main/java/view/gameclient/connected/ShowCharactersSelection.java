package view.gameclient.connected;

import app.kernel.subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketWriter;

public class ShowCharactersSelection implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x09);
        _writer.writeD(0); // number of characters

        _writer.writeD(0); // maximum number of chars allowed
        _writer.writeC(0x00);
    }
}
