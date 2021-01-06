package kernel.database.kernel;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import kernel.core.Kernel;
import kernel.core.KernelModuleInterface;

public class DatabaseKernelModule implements KernelModuleInterface {

    private PersistService persistService;

    @Inject
    public DatabaseKernelModule(PersistService _persistService) {
        this.persistService = _persistService;
    }

    @Override
    public void onBoot(Kernel _kernel) {
        this.persistService.start();
    }

    @Override
    public void onHalt(Kernel _kernel) {
        this.persistService.stop();
    }
}
