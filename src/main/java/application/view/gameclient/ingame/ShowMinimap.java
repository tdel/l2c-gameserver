package application.view.gameclient.ingame;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class ShowMinimap implements OutgoingGameClientPacketInterface {

    private int mapId;

    public ShowMinimap(int _mapId) {
        this.mapId = _mapId;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0xA3);

        _writer.writeD(this.mapId);

        _writer.writeC(0); // Seven Sign period : 0 competition recruiting
    }
}
