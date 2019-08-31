package controller.gameclient.authed;

import com.google.inject.Inject;
import model.entity.L2Character;
import model.entity.instance.PlayerInstance;
import model.repository.Entity.L2CharacterRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.GameClientConnectionState;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.authed.CharacterSelected;


public class RequestCharacterSelection implements IncomingGameClientPacketInterface {


    private final L2CharacterRepository charsRepository;

    @Inject
    public RequestCharacterSelection(L2CharacterRepository _charsRepository) {
        this.charsRepository = _charsRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        int charSlot = _reader.readD();

        L2Character character = this.charsRepository.findOneBySlot(1, charSlot);

        PlayerInstance player = new PlayerInstance(1, character, _client);


        _client.setPlayerInstance(player);
        _client.setConnectionState(GameClientConnectionState.LOADING_GAME);

        _client.sendPacket(new CharacterSelected(player, 1));
    }
}
