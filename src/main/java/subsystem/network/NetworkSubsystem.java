package subsystem.network;

import app.kernel.Kernel;
import subsystem.AbstractKernelSubsystem;
import subsystem.network.core.NetworkServer;
import subsystem.network.gameclient.GameClientServer;
import com.google.inject.Inject;
import subsystem.network.loginserver.LoginServerClient;

public class NetworkSubsystem extends AbstractKernelSubsystem {

    private NetworkServer gameClientServer;

    private LoginServerClient loginServerClient;

    @Inject
    public NetworkSubsystem(Kernel _kernel) {
        super(_kernel);
    }

    @Override
    protected void onModuleStart() throws Exception {
        int gameClientPort = this.getKernelParameter("subsystem.network.gameclient.server.port");

        this.gameClientServer = this.getService(GameClientServer.class);
        this.gameClientServer.start(gameClientPort);


        this.loginServerClient = this.getService(LoginServerClient.class);
        this.loginServerClient.start("127.0.0.1", 1234);

    }

    @Override
    protected void onModuleStop() {
        this.gameClientServer.stop();
    }
}
