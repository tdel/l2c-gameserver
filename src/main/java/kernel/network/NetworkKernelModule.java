package kernel.network;

import kernel.configuration.Config;
import kernel.core.Kernel;
import kernel.core.KernelModuleInterface;
import kernel.network.gameclient.GameClientServer;
import com.google.inject.Inject;
import kernel.network.loginserver.LoginServerClient;

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
        this.gcServer.start(this.config.getInt("kernel.network.gameclient.server.port"));
        this.lsClient.start(this.config.getString("kernel.network.loginserver.client.host"),this.config.getInt("kernel.network.loginserver.client.port"));
    }

    @Override
    public void onHalt(Kernel _kernel) {
        this.gcServer.stop();
    }
}
