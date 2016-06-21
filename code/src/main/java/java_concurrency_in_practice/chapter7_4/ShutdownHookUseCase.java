package java_concurrency_in_practice.chapter7_4;

/**
 * @author bjxieb
 * @date 6/19/16
 */
public class ShutdownHookUseCase {
  public void start() {
    Runtime.getRuntime().addShutdownHook(new Thread(new Hook1()));
  }

  class Hook2 implements Runnable {
    @Override
    public void run() {

      System.out.println("hook is running!");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  class Hook1 implements Runnable {
    @Override
    public void run() {

      System.out.println("hook is running!");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    ShutdownHookUseCase shutdownHookUseCase = new ShutdownHookUseCase();
    shutdownHookUseCase.start();
    System.exit(1);
  }
}
