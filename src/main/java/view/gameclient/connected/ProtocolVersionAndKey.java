package view.gameclient.connected;

import app.kernel.subsystem.network.gameclient.packets.OutgoingGameClientPacketInterface;
import app.kernel.subsystem.network.gameclient.packets.PacketWriter;

public class ProtocolVersionAndKey implements OutgoingGameClientPacketInterface {

    private boolean protocol;
    private byte[] key;
    private int serverId;

    public ProtocolVersionAndKey(boolean _protocol, byte[] _key, int _serverId) {
        this.protocol = _protocol;
        this.key = _key;
        this.serverId = _serverId;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeD(0x2E);

        _writer.writeC(protocol ? 1 : 0);

        // key
        for (int i = 0; i < 8; i++) {
            _writer.writeC(this.key[i]);
        }

        _writer.writeD(0x01);
        _writer.writeD(this.serverId);
        _writer.writeC(0x01);
        _writer.writeD(0x00); // obfuscation key
        _writer.writeC(0x00); // isClassic
    }
}
