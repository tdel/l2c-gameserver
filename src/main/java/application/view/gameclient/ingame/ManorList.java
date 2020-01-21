package application.view.gameclient.ingame;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class ManorList implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0xFE);
        _writer.writeH(0x22);

        _writer.writeD(0); // size of castle !

    }
}
