package java_concurrency_in_practice.chapter7_4;

/**
 * @author bjxieb
 * @date 6/19/16
 */
public class DaemonThreadUseCase {
  public void start() {
    Thread thread = new Thread(new Daemon());
     thread.setDaemon(true);
    thread.start();
  }


  public static void main(String[] args) {
    DaemonThreadUseCase daemonThreadUseCase = new DaemonThreadUseCase();
    daemonThreadUseCase.start();
    try {
      Thread.sleep(3000);
      System.exit(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  class Daemon implements Runnable {

    @Override
    public void run() {
      while (true) {
        System.out.println("I'm john!");
      }
    }
  }
}
