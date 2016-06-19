package _1.chapter7_4;

/**
 * @author bjxieb
 * @date 6/19/16
 */
public class UnkownThreadException implements Thread.UncaughtExceptionHandler {
  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println(t.getName()+":"+e.getMessage());
  }
}
