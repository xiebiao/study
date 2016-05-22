package _1.chapter5_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用{@link CountDownLatch}用于控制线程同时开始执行
 * 
 * @author xiebiao
 * @date 5/9/16
 */
public class SemaphoreTestCase<T extends Thread> {

  private CountDownLatch begin;
  // 当T执行完成后-1
  private CountDownLatch end;
  private List<T> workers;
  private ExecutorService executor;

  public SemaphoreTestCase(List<T> workers, CountDownLatch begin, CountDownLatch end) {
    this.begin = begin;
    this.end = end;
    executor = Executors.newFixedThreadPool(workers.size());
    this.workers = workers;
  }

  public void execute() {
    for (T worker : workers) {
      executor.execute(worker);
    }
    begin.countDown();// Let's go
    try {
      end.await();// 等待workers全部执行完成
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println("Done!");
    }
    executor.shutdown();
  }

  public static void main(String[] args) {
    CountDownLatch begin = new CountDownLatch(1);
    CountDownLatch end = new CountDownLatch(3);
    List<SemaphoreWorker> workers = new ArrayList<>();
    SemaphoreUseCase semaphoreUseCase = new SemaphoreUseCase();
    workers.add(new SemaphoreWorker(semaphoreUseCase, 500));
    workers.add(new SemaphoreWorker(semaphoreUseCase, 400));
    workers.add(new SemaphoreWorker(semaphoreUseCase, 1000));
    SemaphoreTestCase concurrentInvoker = new SemaphoreTestCase(workers, begin, end);
    concurrentInvoker.execute();
  }
}
