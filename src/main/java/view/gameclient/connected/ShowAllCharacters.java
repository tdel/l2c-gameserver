package view.gameclient.connected;

import subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import subsystem.network.gameclient.packets.PacketWriter;

public class ShowAllCharacters implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x09);
        _writer.writeD(0); // number of characters

        _writer.writeD(1); // maximum number of chars allowed
        _writer.writeC(0x00);

        _writer.writeC(0x01); // play mode, if 1 can create only 2 char in regular lobby
        _writer.writeD(0x02); // if 1, korean client
        _writer.writeC(0x00); // if 1 suggest premium account

    }
}
