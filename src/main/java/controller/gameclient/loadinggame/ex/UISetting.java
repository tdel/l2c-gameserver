package controller.gameclient.loadinggame.ex;

import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class UISetting implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0xFE);
        _writer.writeH(0x70);

        _writer.writeD(16); // buffsize
        _writer.writeD(0); // categories

        _writer.writeD(0); // num keys


        _writer.writeD(0x11);
        _writer.writeD(0x10);
    }
}
