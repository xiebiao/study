package java_concurrency_in_practice.chapter5_5;

/**
 * @author xiebiao
 * @date 5/22/16
 */
public class FutureResult {
  private int n;

  public FutureResult(int n) {
    this.n = n;
  }

  public String toString() {
    return "'" + n;
  }
}
