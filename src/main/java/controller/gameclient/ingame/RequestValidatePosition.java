package controller.gameclient.ingame;

import model.instance.PlayerInstance;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.gameclient.ingame.ValidateLocation;

public class RequestValidatePosition implements IncomingGameClientPacketInterface {
    private static final Logger logger = LogManager.getLogger("player-geo");

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        int x = _reader.readD();
        int y = _reader.readD();
        int z = _reader.readD();

        int heading = _reader.readD();
        int data = _reader.readD();

        PlayerInstance player = _client.getPlayer();
        int playerX = player.getCoordinate().getX();
        int playerY = player.getCoordinate().getY();
        int playerZ = player.getCoordinate().getZ();
        int playerHeading = player.getCoordinate().getHeading();

        logger.info("Client position : [ x: " + x + ", y: " + y + ", z: " + z + ", heading: " + heading + "]");
        logger.info("ServerPosition : [ x: " + playerX + ", y: " + playerY + ", z: " + playerZ + ", heading:" + playerHeading + "]");

        player.setCoordinates(x, y, z, heading);

        _client.sendPacket(new ValidateLocation(player));

        // do nothing for the moment.

    }
}
