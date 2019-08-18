package app.main.module;

import app.kernel.subsystem.network.gameclient.GameClientChannelInitializer;
import app.kernel.subsystem.network.gameclient.GameClientServer;
import app.kernel.subsystem.network.gameclient.packets.security.BlowfishKeygen;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class NetworkSubsystemModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(GameClientServer.class).in(Singleton.class);
        this.bind(GameClientChannelInitializer.class).in(Singleton.class);
        this.bind(BlowfishKeygen.class).in(Singleton.class);
    }
}
