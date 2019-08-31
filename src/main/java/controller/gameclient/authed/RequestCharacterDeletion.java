package controller.gameclient.authed;

import com.google.inject.Inject;
import model.entity.L2Character;
import model.repository.Entity.L2CharacterRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.authed.CharacterDeleted;
import view.gameclient.connected.ShowAllCharacters;

import java.util.List;

public class RequestCharacterDeletion implements IncomingGameClientPacketInterface {

    private final L2CharacterRepository charsRepository;

    @Inject
    public RequestCharacterDeletion(L2CharacterRepository _charsRepository) {
        this.charsRepository = _charsRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        int charSlot = _reader.readD(); // start 0

        L2Character character = this.charsRepository.findOneBySlot(1, charSlot);
        this.charsRepository.remove(character);

        _client.sendPacket(new CharacterDeleted());
        _client.sendPacket(new ShowAllCharacters(this.charsRepository.getAllByAccountId(1)));
    }
}
