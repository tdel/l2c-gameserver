package kernel.clock;

import com.google.inject.Inject;

import java.util.Calendar;

public class GameClock {

    private int ticksPerSeconds;
    private int millisecondsPerTick;
    private int ticksPerIGDay;

    private final long referenceTime;

    @Inject
    public GameClock(int _ticksPerSeconds) {
        this.ticksPerSeconds = _ticksPerSeconds;
        this.millisecondsPerTick = 1000 / this.ticksPerSeconds;
        this.ticksPerIGDay = this.getTicksPerIngameDay(_ticksPerSeconds);
        this.referenceTime = this.getReferenceTime();
    }


    public int getGameTime() {
        return (getGameTicks() % this.ticksPerIGDay) / this.millisecondsPerTick;
    }

    public int getGameHour() {
        return getGameTime() / 60;
    }

    public int getGameMinute() {
        return getGameTime() % 60;
    }

    /**
     * The true GameTime tick. Directly taken from current time. This represents the tick of the time.
     */
    public int getGameTicks() {
        return (int) ((this.now() - this.referenceTime) / this.millisecondsPerTick);
    }

    public int getTicksPerSeconds() {
        return this.ticksPerSeconds;
    }

    public long getNextTickTime() {
        return this.now() + this.millisecondsPerTick;
    }

    public long now() {
        return System.currentTimeMillis();
    }

    private int getTicksPerIngameDay(int _ticksPerSeconds) {
        int millisecondsPerIngameDay = (3600000 * 24) / 6; // 6 = InGameDaysPerRealDay
        int secondsPerIngameDay = millisecondsPerIngameDay / 1000;

        return secondsPerIngameDay * _ticksPerSeconds;
    }

    private long getReferenceTime() {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();
    }


}
