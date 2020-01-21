package application.controller.gameclient.ingame;

import application.model.instance.PlayerInstance;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.view.gameclient.ingame.DeleteObject;
import application.view.gameclient.ingame.LeaveWorld;

public class RequestLogout implements IncomingGameClientPacketInterface {


    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        PlayerInstance player = _client.getPlayer();

        player.broadcast(new DeleteObject(player.getId()));
        player.sendPacket(new LeaveWorld());

        _client.disconnect();
    }

}
