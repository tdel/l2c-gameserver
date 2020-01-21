package application.view.gameclient.ingame;

import application.model.instance.Coordinate;
import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

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
    public MoveToLocation(int _objectId, Coordinate _origin, Coordinate _target) {
        this(
                _objectId,
                _origin.getX(),
                _origin.getY(),
                _origin.getZ(),
                _target.getX(),
                _target.getY(),
                _target.getZ()
        );
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
