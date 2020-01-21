package application.view.gameclient.ingame;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class ActionFail implements OutgoingGameClientPacketInterface {

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x1F);
    }
}
