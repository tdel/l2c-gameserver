package application.lobby.response;

import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class AuthResult implements OutgoingGameClientPacketInterface {

    private String account;

    public AuthResult(String _account) {
        this.account = _account;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x05);
        _writer.writeS(this.account);

        _writer.writeD(1);
        _writer.writeD(1);
        _writer.writeD(0);
        _writer.writeD(0);
    }
}
