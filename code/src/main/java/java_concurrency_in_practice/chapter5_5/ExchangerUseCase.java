package java_concurrency_in_practice.chapter5_5;

import java.util.concurrent.*;

/**
 * 两个线程之间进行数据交换
 * 
 * @author bjxieb
 * @date 5/31/16
 */
public class ExchangerUseCase {
  // 开关
  private static volatile boolean off = false;
  private ExecutorService executor;
  private Exchanger<ExchangeData> exchanger = new Exchanger();

  public ExchangerUseCase() {
    executor = Executors.newFixedThreadPool(2);
  }

  static class ExchangeData {
    private String msg;
    private String status;

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }
  }
  static class Reader implements Runnable {
    private Exchanger<ExchangeData> exchanger;

    Reader(Exchanger exchanger) {
      this.exchanger = exchanger;
    }

    @Override
    public void run() {
      while (!off) {
        try {
          ExchangeData response = new ExchangeData();
          response.setStatus("OK");
          ExchangeData data = exchanger.exchange(response, 1l, TimeUnit.SECONDS);
          System.out.println(Thread.currentThread().getName() + " read msg:" + data.getMsg());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (TimeoutException t) {
          System.out.println("等待写操作超时");
        }
      }
    }
  }
  static class Writer implements Runnable {
    private Exchanger<ExchangeData> exchanger;

    Writer(Exchanger exchanger) {
      this.exchanger = exchanger;
    }

    @Override
    public void run() {
      while (!off) {
        try {
          Thread.sleep(2000);
          ExchangeData data = new ExchangeData();
          data.setMsg(System.currentTimeMillis() + "");
          ExchangeData response = exchanger.exchange(data, 1l, TimeUnit.SECONDS);
          System.out
              .println(Thread.currentThread().getName() + " write status:" + response.getStatus());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (TimeoutException t) {
          System.out.println("等待读操作超时");
        }
      }
    }
  }

  public void start() {
    executor.execute(new Thread(new Writer(this.exchanger)));
    executor.execute(new Thread(new Reader(this.exchanger)));
  }

  public void shutdown() {
    off = true;
    executor.shutdown();
  }

  public static void main(String[] args) {
    ExchangerUseCase useCase = new ExchangerUseCase();
    useCase.start();
    try {
      Thread.sleep(5000);
      useCase.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
