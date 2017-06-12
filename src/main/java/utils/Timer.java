package utils;

/**
 * Date: 12.06.2017
 * Time: 16:23
 *
 * @author Anatoliy
 */
public class Timer {
    private long startTimeMillis;

    private static Timer instance;

    public static Timer getInstance() {
        if (null == instance) {
            instance = new Timer();
        }
        return instance;
    }

    private Timer() {
    }

    public void startTimer() {
        startTimeMillis = System.currentTimeMillis();
    }

    public long calculateElapsedTime() {
        final long stopTimeMillis = System.currentTimeMillis();
        return stopTimeMillis - startTimeMillis;
    }
}
