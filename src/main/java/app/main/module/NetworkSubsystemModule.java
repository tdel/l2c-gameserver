package app.main.module;

import subsystem.network.gameclient.GameClientChannelInitializer;
import subsystem.network.gameclient.GameClientServer;
import subsystem.network.gameclient.packets.security.BlowfishKeygen;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import subsystem.network.loginserver.LoginServerChannelInitializer;
import subsystem.network.loginserver.LoginServerClient;
import subsystem.network.loginserver.packets.codec.JSONToPacket;
import subsystem.network.loginserver.packets.codec.PacketToJSON;

public class NetworkSubsystemModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(GameClientServer.class).in(Singleton.class);
        this.bind(GameClientChannelInitializer.class).in(Singleton.class);
        this.bind(BlowfishKeygen.class).in(Singleton.class);

        this.bind(LoginServerClient.class).in(Singleton.class);
        this.bind(LoginServerChannelInitializer.class).in(Singleton.class);
        this.bind(PacketToJSON.class).in(Singleton.class);
        this.bind(JSONToPacket.class).in(Singleton.class);
    }
}
