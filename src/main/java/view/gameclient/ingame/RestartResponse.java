package view.gameclient.ingame;

import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class RestartResponse implements OutgoingGameClientPacketInterface {

    private boolean result;

    public RestartResponse(boolean _result) {
        this.result = _result;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x71);
        _writer.writeD(this.result ? 1 : 0);
    }
}
