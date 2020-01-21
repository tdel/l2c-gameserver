package application.controller.gameclient.loadinggame.ex;

import application.core.controller.ExPacketInterface;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.PacketReader;

public class RequestKeyMapping implements ExPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        _client.sendPacket(new UISetting());
    }

}
