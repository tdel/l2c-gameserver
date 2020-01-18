package view.gameclient.ingame;

import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class ServerClose implements OutgoingGameClientPacketInterface {
    @Override
    public void write(PacketWriter _writer) {

        _writer.writeC(0x20);
    }
}
