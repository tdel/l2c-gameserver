package kernel.clock;


import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameClockTickerTask implements Runnable {

    private static final Logger logger = LogManager.getLogger();

    private GameClock gameClock;
    private List<GameClockTask> tasks;

    private boolean isTicking;

    @Inject
    public GameClockTickerTask(GameClock _gameClock) {
        this.gameClock = _gameClock;
        this.tasks = new ArrayList<>();
        this.isTicking = true;
    }

    public void addTask(GameClockTask _task) {
        this.tasks.add(_task);
    }

    public void stopTicking() {
        this.isTicking = false;
    }

    @Override
    public void run() {

        long nextTickTime;
        long sleepTime;

        while (this.isTicking) {
            nextTickTime = this.gameClock.getNextTickTime();

            for(GameClockTask task : this.tasks) {
                try {
                    task.update();
                } catch (Throwable e) {
                    System.err.println(e);
                }
            }

            sleepTime = nextTickTime - System.currentTimeMillis();
            if (sleepTime > 0) {
                logger.debug("Sleeping " + sleepTime + "ms ");
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }

        }

    }
}
