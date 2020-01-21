package application.controller.gameclient.loadinggame.ex;

import application.core.controller.ExPacketInterface;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.PacketReader;
import application.view.gameclient.ingame.ManorList;

public class RequestManorList implements ExPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        _client.sendPacket(new ManorList());
    }

}
