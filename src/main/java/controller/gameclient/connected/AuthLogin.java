package controller.gameclient.connected;

import com.google.inject.Inject;
import model.entity.L2Character;
import model.repository.Entity.L2CharacterRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.GameClientConnectionState;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.connected.AuthResult;
import view.gameclient.connected.ShowAllCharacters;

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

        _client.setLogin(loginName);

        List<L2Character> chars = this.charsRepository.getAllByAccountId(1);


        _client.setConnectionState(GameClientConnectionState.AUTHED);
        _client.sendPacket(new ShowAllCharacters(chars));
    }
}
