package _1.chapter5_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * CountDownLatch升级版
 *
 * @author bjxieb
 * @date 6/19/16
 */
public class PhaserCountDownLatchUseCase {
    private Phaser phaser = new Phaser(1);
    private ExecutorService executor;

    public void start() {
        executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            phaser.register();
            executor.submit(new PhaserThread(phaser));
        }
        // try {
        // Thread.sleep(100);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        phaser.arriveAndDeregister();
    }

    public void shutdown() {
        // phaser.forceTermination();
        executor.shutdownNow();
    }

    class PhaserThread extends Thread {
        private Phaser phaser;

        public PhaserThread(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {

            phaser.arriveAndAwaitAdvance();
            System.out.println("running");
        }
    }

    public static void main(String[] args) {
        PhaserCountDownLatchUseCase phaserUseCase = new PhaserCountDownLatchUseCase();
        phaserUseCase.start();
        phaserUseCase.shutdown();
    }
}
