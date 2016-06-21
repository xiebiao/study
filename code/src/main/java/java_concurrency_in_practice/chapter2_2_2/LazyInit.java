package java_concurrency_in_practice.chapter2_2_2;

/**
 * @author xiebiao
 * @date 5/21/16
 */
public class LazyInit {
  private Object object = null;

  public Object getInstance() {
    // 当两个线程A,B同时进入的时候，可能看到的object的结果是不一样的
    if (object == null) {// 这里变成一个竞态条件
      object = new Object();
    }
    return object;
  }
}
