package _1.chapter7_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bjxieb
 * @date 6/4/16
 */
public class InterruptUseCase {
  private ExecutorService executorService = Executors.newCachedThreadPool();
  private static final int workerSize = 5;

  public void start() {
    for (int i = 0; i < workerSize; i++) {
      executorService.submit(new Worker());
    }
  }

  public void shutdown() {
    executorService.shutdownNow();
  }

  static class Worker implements Runnable {
    @Override
    public void run() {
      while (Thread.currentThread().isInterrupted() == false) {
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//          return;
//        }
        System.out.println(Thread.currentThread().getName() + ": working!");
      }
    }
  }

  public static void main(String[] args) {
    InterruptUseCase interruptUseCase = new InterruptUseCase();
    interruptUseCase.start();
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      interruptUseCase.shutdown();
  }
}
