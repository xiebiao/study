package java_concurrency_in_practice.chapter13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 队列锁
 *
 * @author bjxieb
 * @date 7/8/16
 */
public class CLHLock implements Lock {
    private final AtomicReference<Node> tail;
    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;

    public CLHLock() {
        tail = new AtomicReference(new Node());
        node = new ThreadLocal() {
            protected Node initialValue() {
                return new Node();
            }
        };
        prev = new ThreadLocal<>();
    }

    @Override
    public void lock() {
        Node my = node.get();
        my.locked = true;
        Node old = tail.getAndSet(my);
        prev.set(old);
        while (old.locked) {
            //如果上一个node被锁住,则一直等待
            //直到被释放
        }
    }

    @Override
    public void unlock() {
        Node _node = this.node.get();
        _node.locked = false;
        this.node.set(prev.get());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }


    private static class Node {
        volatile boolean locked;
    }
}
