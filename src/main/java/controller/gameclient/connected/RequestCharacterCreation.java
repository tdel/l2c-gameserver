package controller.gameclient.connected;

import app.kernel.subsystem.network.gameclient.ChannelHandler;
import app.kernel.subsystem.network.gameclient.packets.IncomingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketReader;
import view.gameclient.connected.CharacterCreationOK;

public class RequestCharacterCreation implements IncomingGameClientPacketInterface {
    @Override
    public void execute(PacketReader _reader, ChannelHandler _client) {

        String name = _reader.readS();
        int race = _reader.readD();
        byte sex = (byte) _reader.readD();
        int classId = _reader.readD();
        int statInt = _reader.readD();
        int statStr = _reader.readD();
        int statCon = _reader.readD();
        int statMen = _reader.readD();
        int statDex = _reader.readD();
        int statWit = _reader.readD();
        byte hairStyle = (byte) _reader.readD();
        byte hairColor = (byte) _reader.readD();
        byte face = (byte) _reader.readD();


        // Last Verified: May 30, 2009 - Gracia Final - Players are able to create characters with names consisting of as little as 1,2,3 letter/number combinations.
        if ((name.length() < 1) || (name.length() > 16)) {
            //_client.sendPacket(new CharCreateFail(CharCreateFail.REASON_16_ENG_CHARS));
            return;
        }

        _client.sendPacket(new CharacterCreationOK());
    }
}
