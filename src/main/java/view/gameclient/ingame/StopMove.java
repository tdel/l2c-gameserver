package view.gameclient.ingame;

import model.instance.Coordinate;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class StopMove implements OutgoingGameClientPacketInterface {

    private int objectId;
    private int x;
    private int y;
    private int z;
    private int heading;

    public StopMove(int _objectId, int _x, int _y, int _z, int _heading) {
        this.objectId = _objectId;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.heading = _heading;
    }

    public StopMove(int _objectId, Coordinate _coordinate) {
        this(
                _objectId,
                _coordinate.getX(),
                _coordinate.getY(),
                _coordinate.getZ(),
                _coordinate.getHeading()
        );
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x47);
        _writer.writeD(this.objectId);
        _writer.writeD(this.x);
        _writer.writeD(this.y);
        _writer.writeD(this.z);
        _writer.writeD(this.heading);
    }
}
