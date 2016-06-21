package java_concurrency_in_practice.chapter3_2;

/**
 * 逸出
 * 
 * @author xiebiao
 * @date 5/22/16
 */
public class ThisEscape {

  public ThisEscape(EventSource source) {
    source.registerListener(new EventListener() {
      // 隐式获得ThisEscape的this引用，但ThisEscape还没有完全构造完成
      @Override
      public void onEvent(Event e) {
        // TODO
      }
    });
    // 在构造方法没有返回之前，this引用都不完整
  }

}
