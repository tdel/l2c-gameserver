package subsystem.network.gameclient.packets;

import subsystem.network.gameclient.ChannelHandler;

public interface IncomingGameClientPacketInterface {
    public void execute(PacketReader _reader, ChannelHandler _client);
}
