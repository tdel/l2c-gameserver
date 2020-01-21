package kernel.network.gameclient.packets;

import kernel.network.gameclient.GameClientChannelHandler;

public interface IncomingGameClientPacketInterface {
    public void execute(PacketReader _reader, GameClientChannelHandler _client);
}
