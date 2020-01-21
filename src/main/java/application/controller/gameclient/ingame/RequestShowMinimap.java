package application.controller.gameclient.ingame;

import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.view.gameclient.ingame.ShowMinimap;

public class RequestShowMinimap implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        _client.sendPacket(new ShowMinimap(1665));
    }
}
