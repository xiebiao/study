package java_concurrency_in_practice.chapter6_15;

import java.util.concurrent.*;

/**
 * @author xiebiao
 * @date 6/4/16
 */
public class CallableUseCase {
  private ExecutorService executorService = Executors.newSingleThreadExecutor();

  public void executeForCallable() {
    Callable<String> callable = new Callable() {
      @Override
      public String call() throws Exception {
        Thread.sleep(5000);
        return "Hello";
      }
    };
    Future<String> f = executorService.submit(callable);
    try {
      String result = f.get();
      System.out.println(result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    executorService.shutdown();
  }

  public void executeFuture() {
    Future<String> result = executorService.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println("Do something");
          try {
              Thread.sleep(3000);
          } catch (InterruptedException e) {
          e.printStackTrace();
          }
      }
    }, new String("OK"));
    try {
      System.out.println(result.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    executorService.shutdown();
  }

  public static void main(String[] args) {
    CallableUseCase callableUseCase = new CallableUseCase();
    // callableUseCase.executeForCallable();
    callableUseCase.executeFuture();
  }
}
