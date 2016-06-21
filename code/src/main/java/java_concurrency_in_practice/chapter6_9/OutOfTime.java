package java_concurrency_in_practice.chapter6_9;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 建议使用{@link java.util.concurrent.Executors#newScheduledThreadPool(int)} 来执行定时任务,<br/> 而不使用 {@link
 * Timer}
 *
 * @author xiebiao
 * @date 6/4/16
 */
public class OutOfTime {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.schedule(new ThrowTask(), 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
