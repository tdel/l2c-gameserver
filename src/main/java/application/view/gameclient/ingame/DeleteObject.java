package application.view.gameclient.ingame;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class DeleteObject implements OutgoingGameClientPacketInterface {

    private final int objectId;

    public DeleteObject(int _objectId) {
        this.objectId = _objectId;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x08);
        _writer.writeD(this.objectId);
        _writer.writeD(0x00); // c2
    }

}
