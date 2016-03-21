package my.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nara007 on 16/3/20.
 */
public class TestOneSky2 {

  public BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);

  // public Thread[] threads = new Thread[4];

  public void init() {
    for (int i = 0; i < 4; i++) {
      new Thread() {
        @Override
        public void run() {
          try {
            for (int i = 0; i < 4; i++) {
              String log = null;
              log = blockingQueue.take();
              TestOneSky2.parseLog(log);
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }.start();
    }
  }

  public static void main(String[] args) {

    System.out.println("begin:" + (System.currentTimeMillis() / 1000));

    TestOneSky2 testOneSky2 = new TestOneSky2();
    testOneSky2.init();
    for (int i = 0; i < 16; i++) {
      final String log = "" + (i + 1);
      {
        // TestOneSky.parseLog(log);
        try {
          testOneSky2.blockingQueue.put(log);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

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
}
