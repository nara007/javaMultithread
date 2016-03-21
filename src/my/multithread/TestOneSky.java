package my.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nara007 on 16/3/20.
 */
public class TestOneSky {

  public ExecutorService pool = Executors.newScheduledThreadPool(4);

  public static void main(String[] args) {

    System.out.println("begin:" + (System.currentTimeMillis() / 1000));

    TestOneSky testOneSky = new TestOneSky();
    for (int i = 0; i < 16; i++) {
      final String log = "" + (i + 1);
      {
        // TestOneSky.parseLog(log);

        testOneSky.pool.execute(testOneSky.getTask(log));
      }
    }

  }

  public static void parseLog(String log) {
    System.out.println("log:" + (System.currentTimeMillis() / 1000));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public MyTask getTask(String log) {
    return new MyTask(log);
  }

  class MyTask implements Runnable {
    String log;

    public MyTask(String log) {
      this.log = log;
    }

    @Override
    public void run() {

      TestOneSky.parseLog(this.log);
    }
  }
}
