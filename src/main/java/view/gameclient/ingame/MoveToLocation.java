package view.gameclient.ingame;

import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class MoveToLocation implements OutgoingGameClientPacketInterface {

    private int objectId;
    private int originX;
    private int originY;
    private int originZ;

    private int targetX;
    private int targetY;
    private int targetZ;


    public MoveToLocation(int _objectId, int _originX, int _originY, int _originZ, int _targetX, int _targetY, int _targetZ) {
        this.objectId = _objectId;
        this.originX = _originX;
        this.originY = _originY;
        this.originZ = _originZ;

        this.targetX = _targetX;
        this.targetY = _targetY;
        this.targetZ = _targetZ;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x2F);

        _writer.writeD(this.objectId);
        _writer.writeD(this.targetX);
        _writer.writeD(this.targetY);
        _writer.writeD(this.targetZ);

        _writer.writeD(this.originX);
        _writer.writeD(this.originY);
        _writer.writeD(this.originZ);
    }
}
