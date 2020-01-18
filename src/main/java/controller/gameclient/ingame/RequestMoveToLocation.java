package controller.gameclient.ingame;

import model.instance.PlayerInstance;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;

public class RequestMoveToLocation implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        PlayerInstance player = _client.getPlayer();

        int targetX = _reader.readD();
        int targetY = _reader.readD();
        int targetZ = _reader.readD();

        int originX = _reader.readD();
        int originY = _reader.readD();
        int originZ = _reader.readD();

        int movementMode = _reader.readD();

        player.setDestinationCoordinates(targetX, targetY, targetZ);
    }
}
