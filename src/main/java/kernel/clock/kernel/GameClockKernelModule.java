package kernel.clock.kernel;

import com.google.inject.Inject;
import kernel.clock.GameClockTickerTask;
import kernel.core.Kernel;
import kernel.core.KernelModuleInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameClockKernelModule implements KernelModuleInterface {

    private static final Logger logger = LogManager.getLogger();

    private GameClockTickerTask gameClockTickerTask;
    private ExecutorService threadPoolExecutor;
    private volatile Future<?> future;

    @Inject
    public GameClockKernelModule(GameClockTickerTask _gameClockTickerTask) {
        this.gameClockTickerTask = _gameClockTickerTask;
        this.threadPoolExecutor = Executors.newFixedThreadPool(1);
    }

    @Override
    public void onBoot(Kernel _kernel) throws Exception {
        logger.info("Starting GameClock ticking");
        this.future = this.threadPoolExecutor.submit(this.gameClockTickerTask);
    }

    @Override
    public void onHalt(Kernel _kernel) {
        this.future.cancel(true);
        logger.info("Stopped GameClock ticking");
    }
}
