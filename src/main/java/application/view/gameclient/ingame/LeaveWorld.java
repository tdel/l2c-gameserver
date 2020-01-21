package application.view.gameclient.ingame;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class LeaveWorld implements OutgoingGameClientPacketInterface {
    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x84);
    }
}
