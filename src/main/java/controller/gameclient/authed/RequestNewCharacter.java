package controller.gameclient.authed;

import com.google.inject.Inject;
import model.entity.template.CharacterTemplate;
import model.repository.template.CharacterTemplateRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.connected.CharactersSelectionList;

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
