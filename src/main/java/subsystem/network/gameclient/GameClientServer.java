package subsystem.network.gameclient;

import subsystem.network.core.NetworkServer;
import com.google.inject.Inject;

public class GameClientServer extends NetworkServer {

    @Inject
    public GameClientServer(GameClientChannelInitializer _channelInitializer) {
        super(_channelInitializer);
    }

}
