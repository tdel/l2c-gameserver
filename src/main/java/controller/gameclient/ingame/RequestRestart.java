package controller.gameclient.ingame;

import com.google.inject.Inject;
import model.entity.L2Character;
import model.instance.PlayerInstance;
import model.repository.Entity.L2CharacterRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.GameClientConnectionState;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.connected.ShowAllCharacters;
import view.gameclient.ingame.DeleteObject;
import view.gameclient.ingame.RestartResponse;

import java.util.List;

public class RequestRestart implements IncomingGameClientPacketInterface {

    private L2CharacterRepository charsRepository;

    @Inject
    public RequestRestart(L2CharacterRepository _charsRepository) {
        this.charsRepository = _charsRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        PlayerInstance player = _client.getPlayer();

        player.broadcast(new DeleteObject(player.getId()));

        _client.setConnectionState(GameClientConnectionState.AUTHED);

        player.sendPacket(new RestartResponse(true));


        List<L2Character> chars = this.charsRepository.getAllByAccountId(1);
        _client.sendPacket(new ShowAllCharacters(chars));
    }
}
