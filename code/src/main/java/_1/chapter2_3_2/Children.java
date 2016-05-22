package _1.chapter2_3_2;

/**
 * @author xiebiao
 * @date 5/21/16
 */
public class Children extends Father {
  // 获得锁
  public synchronized void m() {
    // 调用父类的m，则重新获得该锁
    super.m();
  }
}
