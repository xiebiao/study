package java_concurrency_in_practice.chapter8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author bjxieb
 * @date 6/21/16
 */
public class ThreadPoolUseCase {
    private ExecutorService newCachedThreadPool;
    private ExecutorService newWorkStealingPool;
    private ScheduledExecutorService scheduledExecutorService;

    private void init_newScheduledThreadPool() {
        scheduledExecutorService = Executors.newScheduledThreadPool(5);
    }

    private void init_newCachedThreadPool() {
        newCachedThreadPool = Executors.newCachedThreadPool();
    }

    private void init_newWorkStealingPool() {
        newWorkStealingPool = Executors.newWorkStealingPool();
    }

    public void start() {
     //   init_newScheduledThreadPool();
        init_newWorkStealingPool();
       // scheduledExecutorService.schedule(new Worker(), 3l, TimeUnit.SECONDS);
        newWorkStealingPool.submit(new Worker());
        newWorkStealingPool.shutdown();
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running!");
        }
    }

    public static void main(String[] args) {
        ThreadPoolUseCase threadPoolUseCase = new ThreadPoolUseCase();
        threadPoolUseCase.start();
    }
}
