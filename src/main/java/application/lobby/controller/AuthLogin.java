package application.lobby.controller;

import com.google.inject.Inject;
import application.model.entity.L2Character;
import application.model.repository.Entity.L2CharacterRepository;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.GameClientConnectionState;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.lobby.response.ShowAllCharacters;

import java.util.List;

public class AuthLogin implements IncomingGameClientPacketInterface {

    private final L2CharacterRepository charsRepository;

    @Inject
    public AuthLogin(L2CharacterRepository _charsRepository) {
        this.charsRepository = _charsRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {
        String loginName = _reader.readS().toLowerCase();
        int playKey1 = _reader.readD();
        int playKey2 = _reader.readD();
        int loginKey1 = _reader.readD();
        int loginKey2 = _reader.readD();



        // get info from loginserver



        _client.setLogin(loginName);

        List<L2Character> chars = this.charsRepository.getAllByAccountId(1);


        _client.setConnectionState(GameClientConnectionState.AUTHED);
        _client.sendPacket(new ShowAllCharacters(chars));
    }
}
