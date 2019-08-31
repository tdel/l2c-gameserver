package controller.gameclient.authed;

import com.google.inject.Inject;
import controller.ExPacketInterface;
import model.entity.L2Character;
import model.repository.Entity.L2CharacterRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.PacketReader;
import view.gameclient.connected.ShowAllCharacters;

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

    public void directCall(GameClientChannelHandler _client) {
        List<L2Character> chars = this.charsRepository.getAllByAccountId(1);

        _client.sendPacket(new ShowAllCharacters(chars));
    }
}
