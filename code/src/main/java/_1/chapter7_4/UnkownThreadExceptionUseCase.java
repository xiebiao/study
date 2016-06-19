package _1.chapter7_4;

/**
 * @author bjxieb
 * @date 6/19/16
 */
public class UnkownThreadExceptionUseCase {
  public void start() {
    Thread t = new Thread(new T());
    t.setUncaughtExceptionHandler(new UnkownThreadException());

    t.start();
  }

  class T implements Runnable {
    @Override
    public void run() {
      throw new RuntimeException("unkown exception");
    }
  }

  public static void main(String[] args) {
    UnkownThreadExceptionUseCase u = new UnkownThreadExceptionUseCase();
    u.start();
  }
}
