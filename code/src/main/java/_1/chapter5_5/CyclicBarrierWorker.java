package _1.chapter5_5;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author xiebiao
 * @date 5/22/16
 */
public class CyclicBarrierWorker extends Thread {
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
