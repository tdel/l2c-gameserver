package model.repository;

import com.google.inject.Inject;
import com.google.inject.Provider;
import kernel.Kernel;
import kernel.KernelModuleInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class RepositoryKernelModule implements KernelModuleInterface {

    private static final Logger logger = LogManager.getLogger("kernel");

    private final Map<Class, Provider<PreloadableRepositoryInterface>> repositories;

    @Inject
    public RepositoryKernelModule(Map<Class, Provider<PreloadableRepositoryInterface>> _repositories) {
        this.repositories = _repositories;
    }

    @Override
    public void onBoot(Kernel _kernel) throws Exception {
        for (Provider<PreloadableRepositoryInterface> repository : this.repositories.values()) {
            PreloadableRepositoryInterface rep = repository.get();

            logger.info("Preloading data : " + rep.getClass().getName());
            rep.preload();;
        }
    }

    @Override
    public void onHalt(Kernel _kernel) {

    }
}
