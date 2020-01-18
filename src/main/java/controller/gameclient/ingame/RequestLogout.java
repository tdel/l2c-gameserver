package controller.gameclient.ingame;

import model.instance.PlayerInstance;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.ingame.DeleteObject;
import view.gameclient.ingame.LeaveWorld;

public class RequestLogout implements IncomingGameClientPacketInterface {


    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        PlayerInstance player = _client.getPlayer();

        player.broadcast(new DeleteObject(player.getId()));
        player.sendPacket(new LeaveWorld());

        _client.disconnect();
    }

}
