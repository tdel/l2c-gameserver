package application.controller.gameclient.ingame;

import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;

public class RequestShortcutRegistration implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        int typeId = _reader.readD();
        int slot = _reader.readD();
        int id = _reader.readD();
        int level = _reader.readD();
        int characterType = _reader.readD(); // 1 - player / 2 - pet





    }
}
