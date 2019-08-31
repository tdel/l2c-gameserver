package network;

import com.google.common.graph.Network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.AbstractApplicationModule;
import network.gameclient.GameClientChannelInitializer;
import network.gameclient.GameClientServer;
import network.gameclient.packets.security.BlowfishKeygen;
import com.google.inject.Singleton;
import network.loginserver.LoginServerChannelInitializer;
import network.loginserver.LoginServerClient;
import network.loginserver.packets.codec.JSONToPacket;
import network.loginserver.packets.codec.PacketToJSON;

public class NetworkGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.bind(GameClientServer.class).in(Singleton.class);
        this.bind(GameClientChannelInitializer.class).in(Singleton.class);
        this.bind(BlowfishKeygen.class).in(Singleton.class);

        this.bind(LoginServerClient.class).in(Singleton.class);
        this.bind(LoginServerChannelInitializer.class).in(Singleton.class);
        this.bind(PacketToJSON.class).in(Singleton.class);
        this.bind(JSONToPacket.class).in(Singleton.class);

        Gson gson = new GsonBuilder().serializeNulls().create();
        this.bind(Gson.class).toInstance(gson);

        this.bind(NetworkKernelModule.class).in(Singleton.class);
        this.bindToKernel(NetworkKernelModule.class);

    }
}
