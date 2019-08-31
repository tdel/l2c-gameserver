package network;

import configuration.Config;
import kernel.Kernel;
import kernel.KernelModuleInterface;
import network.gameclient.GameClientServer;
import com.google.inject.Inject;
import network.loginserver.LoginServerClient;

public class NetworkKernelModule implements KernelModuleInterface {

    private final Config config;
    private final GameClientServer gcServer;
    private final LoginServerClient lsClient;

    @Inject
    public NetworkKernelModule(Config _config, GameClientServer _gcServer, LoginServerClient _lsClient) {
        this.config = _config;
        this.gcServer = _gcServer;
        this.lsClient = _lsClient;
    }

    @Override
    public void onBoot(Kernel _kernel) throws Exception {
        this.gcServer.start(this.config.getInt("network.gameclient.server.port"));
        this.lsClient.start(this.config.getString("network.loginserver.client.host"),this.config.getInt("network.loginserver.client.port"));
    }

    @Override
    public void onHalt(Kernel _kernel) {
        this.gcServer.stop();
    }
}
