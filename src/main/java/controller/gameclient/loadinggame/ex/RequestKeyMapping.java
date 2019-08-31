package controller.gameclient.loadinggame.ex;

import controller.ExPacketInterface;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.PacketReader;

public class RequestKeyMapping implements ExPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        _client.sendPacket(new UISetting());
    }

}
