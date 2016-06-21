package java_concurrency_in_practice.chapter5_18;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author bjxieb
 * @date 6/1/16
 */
public class MemoryCache<A, V> implements Computable<A, V> {
  private ConcurrentHashMap<A, FutureTask<V>> cache = new ConcurrentHashMap<>();
  private Computable<A, V> c;

  public MemoryCache(Computable c) {
    this.c = c;
  }

  @Override
  public V compute(final A arg) {
    while (true) {
      FutureTask<V> f = cache.get(arg);
      if (f == null) {
        Callable<V> callable = new Callable<V>() {
          @Override
          public V call() throws Exception {
            return c.compute(arg);
          }
        };
        FutureTask<V> futureTask = new FutureTask(callable);
        f = cache.putIfAbsent(arg, futureTask);
        if (f == null) {
          f = futureTask;
          futureTask.run();
        }
      }
      try {
        return f.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
        // TODO 清除缓存
      } catch (ExecutionException e) {
        e.printStackTrace();
        // TODO 清除缓存
      }
    }

  }
}
