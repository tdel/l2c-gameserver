package application.lobby.controller;

import com.google.inject.Inject;
import application.core.controller.ExPacketInterface;
import application.model.entity.L2Character;
import application.model.repository.Entity.L2CharacterRepository;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.ShowAllCharacters;

import java.util.List;

public class RequestGoToCharacterSelection implements ExPacketInterface {

    private L2CharacterRepository charsRepository;

    @Inject
    public RequestGoToCharacterSelection(L2CharacterRepository _charsRepository) {
        this.charsRepository = _charsRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        List<L2Character> chars = this.charsRepository.getAllByAccountId(1);

        _client.sendPacket(new ShowAllCharacters(chars));
    }

}
