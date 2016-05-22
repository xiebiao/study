package _1.chapter5_5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 场景：异步执行任务A(比如提前加载耗时的数据)，其他任务依赖任务A的返回数据时，处于阻塞状态知道任务A返回
 * 
 * @author xiebiao
 * @date 5/22/16
 */
public class FutureTaskUseCase {
  private int age;
  private final FutureTask<FutureResult> futureTask = new FutureTask<>(
      new Callable<FutureResult>() {
        @Override
        public FutureResult call() throws Exception {
          Thread.sleep(1000);
          System.out.println("Sleep end!" + age);
          return new FutureResult(1);
        }
      });
  private FutureTask<FutureResult> futureTask2;
  private FutureTaskWorker worker = new FutureTaskWorker(futureTask);

  public FutureTaskUseCase() {}

  public void start() {
    worker.start();
  }

  public FutureResult get() throws InterruptedException, ExecutionException {
    return futureTask.get();// 线程会一直阻塞
  }

  public void todo(Integer age) {
    futureTask2 = new FutureTask<FutureResult>(new Callable<FutureResult>() {
      @Override
      public FutureResult call() throws Exception {
        // 不能访问方法参数age，因为无法预知参数的可见性,除非age是final的
        return null;
      }
    });
  }

  public static void main(String[] args) {
    FutureTaskUseCase futureTaskUseCase = new FutureTaskUseCase();
    try {
      futureTaskUseCase.start();
      System.out.println(futureTaskUseCase.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
