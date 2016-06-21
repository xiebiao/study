package java_concurrency_in_practice.chapter8.queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * 无界优先队列{@link java.util.concurrent.PriorityBlockingQueue}
 *
 * @author bjxieb
 * @date 6/21/16
 */
public class PriorityBlockingQueueUseCase {
    private ExecutorService executorService;
    private static final int size = 10;


    private PriorityBlockingQueue<Runnable> priorityBlockingQueue;

    private void init() {
        priorityBlockingQueue = new PriorityBlockingQueue();
        executorService = new ThreadPoolExecutor(1, size, 1000l, MICROSECONDS, priorityBlockingQueue);
    }

    public void start() {
        init();
        //接收任务
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int num = 50;
                while (num-- > 0) {
                    priorityBlockingQueue.put(new Worker(r.nextInt(100)));
                }
            }
        });
        executorService.shutdown();
    }

    /**
     *
     */
    static class Worker implements Runnable, java.lang.Comparable<Worker> {
        private long id;

        public Worker(long id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id + " : running");
        }

        /**
         * 实现优先策略
         */
        @Override
        public int compareTo(Worker o) {
            return Long.valueOf(this.id - o.id).intValue();
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueueUseCase useCase = new PriorityBlockingQueueUseCase();
        useCase.start();
    }
}
