package application.lobby.controller;

import com.google.inject.Inject;
import application.model.entity.template.CharacterTemplate;
import application.model.repository.template.CharacterTemplateRepository;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.CharactersSelectionList;

import java.util.List;

public class RequestNewCharacter implements IncomingGameClientPacketInterface {

    private final CharacterTemplateRepository ctRepository;

    @Inject
    public RequestNewCharacter(CharacterTemplateRepository _ctRepository) {
        this.ctRepository = _ctRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        List<CharacterTemplate> charsTemplate = this.ctRepository.findAllBaseClasses();

        _client.sendPacket(new CharactersSelectionList(charsTemplate));
    }

}
