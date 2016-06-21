package java_concurrency_in_practice.chapter6_15;

import java.util.concurrent.*;

/**
 * 可以将一个较耗时的任务拆分到多个独立任务中去执行，可单独处理任务的结果
 * 
 * @author xiebiao
 * @date 6/4/16
 */
public class CompletionServiceUseCase {
  private ExecutorService executor = Executors.newCachedThreadPool();
  private ExecutorCompletionService completionService = new ExecutorCompletionService(executor);
  private final static int taskSize = 5;

  public void execute() {
    int init = taskSize;
    while (init-- > 0) {
      completionService.submit(new SleepCallable(3000));
    }
    try {
      int r = taskSize;
      while (r-- > 0) {
        System.out.println(completionService.take().get());
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }

  }

  static class SleepCallable implements Callable<java.lang.String> {
    private long sleep;

    public SleepCallable(long sleep) {
      this.sleep = sleep;
    }

    @Override
    public java.lang.String call() throws Exception {
      Thread.sleep(sleep);
      // throw new RuntimeException();
      return Thread.currentThread().getName() + ":Done!";
    }
  }

  public static void main(String[] args) {
    CompletionServiceUseCase completionServiceUseCase = new CompletionServiceUseCase();
    completionServiceUseCase.execute();
  }
}
