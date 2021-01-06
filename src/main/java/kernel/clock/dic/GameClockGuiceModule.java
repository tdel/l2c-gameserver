package kernel.clock.dic;

import kernel.clock.GameClock;
import kernel.clock.GameClockTickerTask;
import kernel.clock.kernel.GameClockKernelModule;
import main.AbstractApplicationModule;

import javax.inject.Singleton;

public class GameClockGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        GameClock gameClock = new GameClock(10);
        this.bind(GameClock.class).toInstance(gameClock);

        this.bind(GameClockTickerTask.class).in(Singleton.class);

        this.bindToKernel(GameClockKernelModule.class);
    }
}
