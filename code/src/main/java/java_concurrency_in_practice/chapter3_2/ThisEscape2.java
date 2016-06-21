package java_concurrency_in_practice.chapter3_2;

/**
 * 逸出
 * 
 * @author xiebiao
 * @date 5/22/16
 */
public class ThisEscape2 {
  public ThisEscape2() {
    // 该线程获得ThisEscape2 this引用
    Thread thread = new Thread();
    thread.start();// 最好不要在这里启动
  }
}
