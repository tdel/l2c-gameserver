package application.controller.gameclient.loadinggame;

import com.google.inject.Inject;
import application.model.instance.PlayerInstance;
import application.model.service.World;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.GameClientConnectionState;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;

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
