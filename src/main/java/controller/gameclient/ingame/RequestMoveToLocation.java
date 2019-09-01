package controller.gameclient.ingame;

import model.entity.instance.PlayerInstance;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.ingame.ActionFail;
import view.gameclient.ingame.MoveToLocation;
import view.gameclient.ingame.StopMove;

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


        //targetZ += player.getCollisionHeight(); // geodata ?
        if ((targetX == originX) && (targetY == originY) && (targetZ == originZ)) {
            _client.sendPacket(new StopMove(player.getId(), originX, originY, originZ, player.getHeading()));

            _client.sendPacket(new ActionFail());
        }

        player.setDestinationCoordinates(targetX, targetY, targetZ);

        player.broadcast(new MoveToLocation(player.getId(), originX, originY, originZ, targetX, targetY, targetZ));
    }
}
