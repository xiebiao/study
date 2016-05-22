package _1.chapter5_5;

import java.util.concurrent.Semaphore;

/**
 * 在大多数情况下，我们的资源需要并发访问，但在有些情况下，我们需要控制资源并发被访问， <br/>
 * 
 * 
 * @author xiebiao
 * @date 5/22/16
 */
public class SemaphoreUseCase {
  private Integer age = 18;
  // 只有一个凭证，实现age只能同时被一个线程访问
  private final Semaphore semaphore = new Semaphore(1);

  public Integer getAge(long useTime) throws InterruptedException {
    try {
      // semaphore.tryAcquire();
      semaphore.acquire();// 一直阻塞
      Thread.sleep(useTime);
      age++;
      System.out.println(Thread.currentThread().getName() + " sleep " + useTime + "ms  End!");
      return age;
    } finally {
      semaphore.release();
    }
  }

}
