package view.gameclient.ingame;

import model.entity.instance.PlayerInstance;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class ValidateLocation implements OutgoingGameClientPacketInterface {

    private PlayerInstance player;

    public ValidateLocation(PlayerInstance _player) {
        this.player = _player;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x79);

        _writer.writeD(this.player.getId());
        _writer.writeD(this.player.getX());
        _writer.writeD(this.player.getY());
        _writer.writeD(this.player.getZ());
        _writer.writeD(this.player.getHeading());

    }
}
