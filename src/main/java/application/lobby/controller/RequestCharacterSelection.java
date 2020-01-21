package application.lobby.controller;

import com.google.inject.Inject;
import application.model.entity.L2Character;
import application.model.instance.PlayerInstance;
import application.model.repository.Entity.L2CharacterRepository;
import application.model.service.World;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.GameClientConnectionState;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.CharacterSelected;


public class RequestCharacterSelection implements IncomingGameClientPacketInterface {


    private final L2CharacterRepository charsRepository;
    private final World world;

    @Inject
    public RequestCharacterSelection(World _world, L2CharacterRepository _charsRepository) {
        this.world = _world;
        this.charsRepository = _charsRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        int charSlot = _reader.readD();

        L2Character character = this.charsRepository.findOneBySlot(1, charSlot);

        PlayerInstance player = new PlayerInstance(this.world, character.getId(), character, _client);


        _client.setPlayerInstance(player);
        _client.setConnectionState(GameClientConnectionState.LOADING_GAME);

        _client.sendPacket(new CharacterSelected(player, 1));
    }
}
