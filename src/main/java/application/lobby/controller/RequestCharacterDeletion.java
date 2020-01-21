package application.lobby.controller;

import com.google.inject.Inject;
import application.model.entity.L2Character;
import application.model.repository.Entity.L2CharacterRepository;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.CharacterDeleted;
import application.lobby.response.ShowAllCharacters;

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
