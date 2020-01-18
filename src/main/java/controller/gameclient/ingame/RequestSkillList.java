package controller.gameclient.ingame;

import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.ingame.SkillsList;

public class RequestSkillList implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        _client.sendPacket(new SkillsList(_client.getPlayer().getSkills()));
    }
}
