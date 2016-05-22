package _1.chapter5_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 与{@link java.util.concurrent.CountDownLatch} 相反，CyclicBarrier是用于线程之间等待
 * 
 * @author xiebiao
 * @date 5/22/16
 */
public class CyclicBarrierUseCase {
  private volatile boolean isAdd = true;
  private AtomicInteger count = new AtomicInteger(0);
  private final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
    @Override
    public void run() {
      if (isAdd) {
        isAdd = false;
      } else {
        isAdd = true;
      }
      System.out.println(count.get());
    }/* 当所有线程都通过栅栏，则执行该线程（可以实现通知机制） */
  });

  private List<CyclicBarrierWorker> workers = new ArrayList<>();
  private ExecutorService executor;

  public CyclicBarrierUseCase() {
    executor = Executors.newFixedThreadPool(4);

  }

  public boolean isAdd() {
    return Boolean.valueOf(this.isAdd);
  }

  public CyclicBarrier getCyclicBarrier() {
    return cyclicBarrier;
  }

  public void decr() {
    this.count.decrementAndGet();
  }

  public void incr() {
    this.count.incrementAndGet();
  }

  public void start() {
    workers.add(new CyclicBarrierWorker(this, 100));
    workers.add(new CyclicBarrierWorker(this, 300));
    workers.add(new CyclicBarrierWorker(this, 400));
    workers.add(new CyclicBarrierWorker(this, 600));
    for (CyclicBarrierWorker worker : workers) {
      executor.execute(worker);
    }
  }

  public static void main(String[] args) {
    CyclicBarrierUseCase cyclicBarrierUseCase = new CyclicBarrierUseCase();
    cyclicBarrierUseCase.start();
  }
}
