package controller.gameclient.loadinggame;

import model.entity.instance.PlayerInstance;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.GameClientConnectionState;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.ingame.ActionFail;
import view.gameclient.ingame.PlayerInfo;
import view.gameclient.ingame.ValidateLocation;

public class EnterWorld implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        _client.setConnectionState(GameClientConnectionState.INGAME);

        PlayerInstance player = _client.getPlayer();



        _client.sendPacket(new PlayerInfo(player));

        // Prevent relogin in game gfx.
        _client.sendPacket(new ValidateLocation(player));

        // Unstuck players that had client open when server crashed.
        _client.sendPacket(new ActionFail());
    }
}
