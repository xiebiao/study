package java_concurrency_in_practice.chapter7_4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author bjxieb
 * @date 6/21/16
 */
public class ExecutorServiceInvokeTestCase {
    private ExecutorService executorService;
    private List<Worker> workerList;

    private void init() {
        executorService = Executors.newCachedThreadPool();
        workerList = new ArrayList<>();
        workerList.add(new Worker(1000));
        workerList.add(new Worker(100));
        workerList.add(new Worker(500));
    }

    public void invokeAll() {
        init();
        try {
            //invokeAll会阻塞
            List<Future<Object>> results = executorService.invokeAll(workerList);
            Iterator<Future<Object>> iterator = results.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public void invokeAny() {

        init();
        try {
            //invokeAll会阻塞
            Object any = executorService.invokeAny(workerList);
            System.out.println(any);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    static class Worker implements Callable<Object> {
        private long sleep;

        public Worker(long sleep) {
            this.sleep = sleep;
        }

        @Override
        public Object call() throws Exception {

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " InterruptedException");
            }

            return Thread.currentThread().getName() + " sleep:" + sleep;
        }
    }

    public static void main(String[] args) {
        ExecutorServiceInvokeTestCase t = new ExecutorServiceInvokeTestCase();
        //   t.invokeAll();
        t.invokeAny();
    }
}
