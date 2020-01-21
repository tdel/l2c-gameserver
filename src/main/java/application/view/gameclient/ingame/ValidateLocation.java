package application.view.gameclient.ingame;

import application.model.instance.PlayerInstance;
import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class ValidateLocation implements OutgoingGameClientPacketInterface {

    private PlayerInstance player;

    public ValidateLocation(PlayerInstance _player) {
        this.player = _player;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x79);

        _writer.writeD(this.player.getId());
        _writer.writeD(this.player.getCoordinate().getX());
        _writer.writeD(this.player.getCoordinate().getY());
        _writer.writeD(this.player.getCoordinate().getZ());
        _writer.writeD(this.player.getHeading());

    }
}
