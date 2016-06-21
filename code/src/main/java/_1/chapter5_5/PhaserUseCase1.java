package _1.chapter5_5;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * CountDownLatch升级版
 *
 * @author bjxieb
 * @date 6/19/16
 */
public class PhaserUseCase1 {
    private static Logger logger = Logger.getLogger(PhaserUseCase1.class);
    private static final int taskSize = 5;
    private Phaser phaser = new Phaser(taskSize);
    private ExecutorService executor;

    public void start() {
        executor = Executors.newFixedThreadPool(taskSize);
        int init = taskSize;
        for (int i = 0; i < init; i++) {
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
            //等待其他线程到达
            phaser.arriveAndAwaitAdvance();
            //如果在等待的过程中,可以中断该线程,或者超时,则使用以下方法
            //awaitAdvanceInterruptibly(int phase)
            //awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit)
            logger.debug("running");
        }
    }

    public static void main(String[] args) {
        PhaserUseCase1 phaserUseCase = new PhaserUseCase1();
        phaserUseCase.start();
        phaserUseCase.shutdown();
    }
}
