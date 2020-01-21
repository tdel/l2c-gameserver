package application.controller.gameclient.ingame;

import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.view.gameclient.ingame.SkillsList;

public class RequestSkillList implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        _client.sendPacket(new SkillsList(_client.getPlayer().getSkills()));
    }
}
