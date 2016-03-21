package my.multithread;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nara007 on 16/3/18.
 */
public class JoinThread extends Thread {
//  public static volatile int n = 0;
  public static AtomicInteger n  = new AtomicInteger(0);

  @Override
  public void run() {

    for (int i = 0; i < 10; i++) {
      n.incrementAndGet();
    }
  }

  public static void main(String[] args) {

    Thread threads[] = new Thread[100];
    for (int i = 0; i < threads.length; i++) {
      // 建立100个线程
      threads[i] = new JoinThread();
    }

    for (int i = 0; i < threads.length; i++) {
      // 运行刚才建立的100个线程
      threads[i].start();
    }

    for (int i = 0; i < threads.length; i++)
    // 100个线程都执行完后继续
    {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }


    System.out.println("n == " + JoinThread.n);
  }
}
