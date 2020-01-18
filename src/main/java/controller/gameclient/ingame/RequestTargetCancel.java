package controller.gameclient.ingame;

import com.google.inject.Inject;
import model.instance.PlayerInstance;
import model.service.World;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.ingame.TargetUnselected;

public class RequestTargetCancel implements IncomingGameClientPacketInterface {

    private final World world;

    @Inject
    public RequestTargetCancel(World _world) {
        this.world = _world;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        int targetId = _reader.readH();


        PlayerInstance target = this.world.getPlayer(targetId);

        _client.sendPacket(new TargetUnselected(target));

    }
}
