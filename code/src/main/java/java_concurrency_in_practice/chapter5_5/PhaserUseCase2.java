package java_concurrency_in_practice.chapter5_5;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * 外部触发线程执行
 *
 * @author bjxieb
 * @date 6/19/16
 */
public class PhaserUseCase2 {
    private static Logger logger = Logger.getLogger(PhaserUseCase2.class);
    private static final int taskSize = 5;
    private Phaser phaser = new Phaser(1);
    private ExecutorService executor;

    public void start() {
        executor = Executors.newFixedThreadPool(taskSize);
        for (int i = 0; i < taskSize; i++) {
            executor.submit(new PhaserThread(phaser));
        }
        //触发执行任务
        phaser.arriveAndDeregister();
    }

    public void shutdown() {
        executor.shutdown();
    }

    class PhaserThread extends Thread {
        private Phaser phaser;

        public PhaserThread(Phaser phaser) {
            this.phaser = phaser;
            //注册任务
            this.phaser.register();
        }

        @Override
        public void run() {
            //等待其他线程到达
            phaser.arriveAndAwaitAdvance();
            logger.debug("running");
        }
    }

    public static void main(String[] args) {
        PhaserUseCase2 phaserUseCase = new PhaserUseCase2();
        phaserUseCase.start();
        phaserUseCase.shutdown();
    }
}
