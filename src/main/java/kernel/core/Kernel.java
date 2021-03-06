package kernel.core;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Kernel {

    private static final Logger logger = LogManager.getLogger("kernel");

    private KernelStatus status;

    private final Set<KernelModuleInterface> modules;

    @Inject
    public Kernel(Set<KernelModuleInterface> _modules) {
        this.status = KernelStatus.STOPED;
        this.modules = _modules;
    }

    private void setStatus(KernelStatus _status) {
        logger.info("Switch state from " + this.status + " to " + _status);
        this.status = _status;
    }

    public void start() throws Exception {
        if (this.status != KernelStatus.STOPED) {
            throw new IllegalStateException("Kernel must be stopped to be started !");
        }
        this.setStatus(KernelStatus.STARTING);

        for (KernelModuleInterface module : this.modules) {
            logger.info("Loading module : " + module.getClass().getName());
            module.onBoot(this);
        }

        this.setStatus(KernelStatus.RUNNING);
    }

    public void halt() {
        if (this.status != KernelStatus.RUNNING) {
            throw new IllegalStateException("Kernel must be running to be stopped");
        }

        this.setStatus(KernelStatus.STOPING);

        for (KernelModuleInterface module : this.modules) {
            module.onHalt(this);
        }

        this.setStatus(KernelStatus.STOPED);
    }

}
