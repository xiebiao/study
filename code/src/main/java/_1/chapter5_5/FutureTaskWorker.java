package _1.chapter5_5;

import java.util.concurrent.FutureTask;

/**
 * @author xiebiao
 * @date 5/9/16
 */
public class FutureTaskWorker extends Thread {
  private FutureTask futureTask;

  public FutureTaskWorker(FutureTask future) {
    this.futureTask = future;
  }

  @Override
  public void run() {
    // TODO 启动futureTask
    futureTask.run();
  }
}
