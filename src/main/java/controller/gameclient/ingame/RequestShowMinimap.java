package controller.gameclient.ingame;

import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.ingame.ShowMinimap;

public class RequestShowMinimap implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        _client.sendPacket(new ShowMinimap(1665));
    }
}
