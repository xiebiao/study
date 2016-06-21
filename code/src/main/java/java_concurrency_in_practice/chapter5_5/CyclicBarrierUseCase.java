package java_concurrency_in_practice.chapter5_5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 与{@link java.util.concurrent.CountDownLatch} 相反，CyclicBarrier是用于线程之间等待
 *
 * @author xiebiao
 * @date 5/22/16
 */
public class CyclicBarrierUseCase {
    private volatile boolean isAdd = true;
    private static final int taskSize = 5;
    private AtomicInteger count = new AtomicInteger(0);
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(taskSize, new Runnable() {
        @Override
        public void run() {
            if (isAdd) {
                isAdd = false;
            } else {
                isAdd = true;
            }
            System.out.println(count.get());
        }/* 当所有线程都通过栅栏，则执行该线程（可以实现通知机制） */
    });

    private ExecutorService executor;

    public CyclicBarrierUseCase() {

    }

    public boolean isAdd() {
        return Boolean.valueOf(this.isAdd);
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public void decr() {
        this.count.decrementAndGet();
    }

    public void incr() {
        this.count.incrementAndGet();
    }

    public void start() {
        executor = Executors.newFixedThreadPool(taskSize);
        int total = taskSize;
        for (int i = 0; i < total; i++) {
            executor.execute(new CyclicBarrierWorker(this, 100));
        }
    }

    static class CyclicBarrierWorker extends Thread {
        private CyclicBarrierUseCase cylicBarrier;
        private long useTime;

        public CyclicBarrierWorker(CyclicBarrierUseCase cyclicBarrier, long useTime) {
            this.cylicBarrier = cyclicBarrier;
            this.useTime = useTime;
        }

        /**
         * 实现所有线程要么同时做加，要么同时做减
         */
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(useTime);
                    if (this.cylicBarrier.isAdd()) {
                        this.cylicBarrier.incr();
                    } else {
                        this.cylicBarrier.decr();
                    }
                    this.cylicBarrier.getCyclicBarrier().await();// 等待其他线程执行到达，直到到达线程数等于{@link
                    // Seamphore#parties}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrierUseCase cyclicBarrierUseCase = new CyclicBarrierUseCase();
        cyclicBarrierUseCase.start();
    }
}
