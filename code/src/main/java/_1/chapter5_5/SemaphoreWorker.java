package _1.chapter5_5;

/**
 * @author xiebiao
 * @date 5/22/16
 */
public class SemaphoreWorker extends Thread {
  private SemaphoreUseCase semaphoreUseCase;
  private long useTime;

  public SemaphoreWorker(SemaphoreUseCase semaphoreUseCase, long sleep) {
    this.semaphoreUseCase = semaphoreUseCase;
    this.useTime = sleep;
  }

  @Override
  public void run() {
    try {
      System.out.println(semaphoreUseCase.getAge(useTime));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    SemaphoreUseCase semaphoreUseCase = new SemaphoreUseCase();
    new Thread(new SemaphoreWorker(semaphoreUseCase, 500)).start();
    new Thread(new SemaphoreWorker(semaphoreUseCase, 1000)).start();
  }
}
