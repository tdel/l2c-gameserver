package controller.gameclient.loadinggame;

import com.google.inject.Inject;
import model.instance.PlayerInstance;
import model.service.World;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.GameClientConnectionState;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;

public class EnterWorld implements IncomingGameClientPacketInterface {

    private final World world;

    @Inject
    public EnterWorld(World _world) {
        this.world = _world;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        _client.setConnectionState(GameClientConnectionState.INGAME);
        PlayerInstance player = _client.getPlayer();

        this.world.addPlayer(player);
    }
}
