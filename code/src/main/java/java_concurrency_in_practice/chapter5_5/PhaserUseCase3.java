package java_concurrency_in_practice.chapter5_5;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * CyclicBarrier升级版
 *
 * @author bjxieb
 * @date 6/19/16
 * @see {@link java.util.concurrent.CyclicBarrier}
 */
public class PhaserUseCase3 {
    private static Logger logger = Logger.getLogger(PhaserUseCase3.class);
    private static final int taskSize = 5;
    final int timesForStop = 3;
    private Phaser phaser = new Phaser(taskSize) {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            //1.控制任务执行
            //2.统计任务
            System.out.println("====== " + phase + " ======");
            return phase >= timesForStop || registeredParties == 0;
        }
    };
    private ExecutorService executor;

    public void start() {
        executor = Executors.newFixedThreadPool(taskSize);
        for (int i = 0; i < taskSize; i++) {
            executor.submit(new PhaserThread(phaser));
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

    class PhaserThread extends Thread {
        private Phaser phaser;

        public PhaserThread(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            do {
                logger.debug("running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //等待其他线程执行到达
                phaser.arriveAndAwaitAdvance();
            } while (phaser.isTerminated() == false);

        }
    }

    public static void main(String[] args) {
        PhaserUseCase3 phaserUseCase = new PhaserUseCase3();
        phaserUseCase.start();
        phaserUseCase.shutdown();
    }
}
