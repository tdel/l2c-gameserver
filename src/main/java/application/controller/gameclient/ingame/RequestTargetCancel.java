package application.controller.gameclient.ingame;

import com.google.inject.Inject;
import application.model.instance.PlayerInstance;
import application.model.service.World;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.view.gameclient.ingame.TargetUnselected;

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
