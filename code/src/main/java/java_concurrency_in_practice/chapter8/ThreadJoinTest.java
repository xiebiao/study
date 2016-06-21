package java_concurrency_in_practice.chapter8;

/**
 * @author bjxieb
 * @date 6/21/16
 */
public class ThreadJoinTest {
    public void start() {
        Thread first = new Thread(new Worker(1000));
        Thread second = new Thread(new Worker(1000));
        Thread third = new Thread(new Worker(100));
        first.start();
        second.start();
        third.start();

        try {
            //first.join();
          //  second.join();
           third.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Worker implements Runnable {
        private long sleep;

        public Worker(long sleep) {
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleep);
                System.out.println(Thread.currentThread().getName() + ":running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadJoinTest joinTest = new ThreadJoinTest();
        joinTest.start();
    }

}
