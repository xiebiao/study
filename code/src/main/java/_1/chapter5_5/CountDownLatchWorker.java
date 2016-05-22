package _1.chapter5_5;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiebiao
 * @date 5/9/16
 */
public class CountDownLatchWorker extends Thread {
  private CountDownLatch begin;
  private CountDownLatch end;

  public CountDownLatchWorker(CountDownLatch begin, CountDownLatch end) {
    this.begin = begin;
    this.end = end;

  }

  @Override
  public void run() {
    try {
      begin.await(); //
      // TODO do something
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(Thread.currentThread().getName() + " Done!");
      end.countDown();
    }
  }
}
