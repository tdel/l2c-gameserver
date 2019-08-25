package view.gameclient.connected;

import subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import subsystem.network.gameclient.packets.PacketWriter;

public class CharactersSelectionList implements OutgoingGameClientPacketInterface {
    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x0D);

        _writer.writeD(0);

    }
}
