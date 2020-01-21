package kernel.configuration;

import com.google.inject.Inject;
import kernel.core.Kernel;
import kernel.core.KernelModuleInterface;

public class ConfigKernelModule implements KernelModuleInterface {

    private final Config config;
    private final String filename;

    @Inject
    public ConfigKernelModule(Config _config, String _configFilename) {
        this.config = _config;
        this.filename = _configFilename;
    }

    @Override
    public void onBoot(Kernel _kernel) throws Exception {
        this.config.load(this.filename);
    }

    @Override
    public void onHalt(Kernel _kernel) {

    }
}
