package java_concurrency_in_practice.chapter5_18;

/**
 * @author bjxieb
 * @date 6/1/16
 */
public interface Computable<A, V> {
  V compute(final  A a);
}

