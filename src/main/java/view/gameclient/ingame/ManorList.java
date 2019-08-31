package view.gameclient.ingame;

import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class ManorList implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0xFE);
        _writer.writeH(0x22);

        _writer.writeD(0); // size of castle !

    }
}
