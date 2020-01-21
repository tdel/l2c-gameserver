package application.controller.gameclient.ingame;

import com.google.inject.Inject;
import application.model.entity.L2Character;
import application.model.instance.PlayerInstance;
import application.model.repository.Entity.L2CharacterRepository;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.GameClientConnectionState;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.ShowAllCharacters;
import application.view.gameclient.ingame.DeleteObject;
import application.view.gameclient.ingame.RestartResponse;

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
