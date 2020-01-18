package view.gameclient.ingame;

import model.instance.PlayerInstance;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class TargetUnselected implements OutgoingGameClientPacketInterface {

    private PlayerInstance target;

    public TargetUnselected(PlayerInstance _target) {
        this.target = _target;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x24);

        _writer.writeD(this.target.getId());
        _writer.writeD(this.target.getCoordinate().getX());
        _writer.writeD(this.target.getCoordinate().getY());
        _writer.writeD(this.target.getCoordinate().getZ());
        _writer.writeD(0x00);
    }
}
