package my.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by nara007 on 16/3/21.
 */
public class TestTwoSky {

  public SynchronousQueue<String> synchronousQueue = new SynchronousQueue<String>();

  public Semaphore semaphore = new Semaphore(1);


  public static void main(String[] args) {

    TestTwoSky testTwoSky = new TestTwoSky();
    System.out.println("begin:" + System.currentTimeMillis() / 1000);


    TestDo testDo = testTwoSky.getInnerClass();

    for (int i = 0; i < 10; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {

          try {
            testTwoSky.semaphore.acquire();

            String input = testTwoSky.synchronousQueue.take();
            String output = testDo.doSome(input);

            System.out.println(System.currentTimeMillis() / 1000 + ":" + output);
            // System.out.println(output);
            testTwoSky.semaphore.release();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }



        }
      }).start();
    }


    for (int i = 0; i < 10; i++) {
      String input = i + "";

      try {
        testTwoSky.synchronousQueue.put(input);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      // String output = testTwoSky.getInnerClass().doSome(input);
      // System.out.println(System.currentTimeMillis() / 1000 + ":" + output);
    }
  }


  public TestDo getInnerClass() {
    return new TestDo();
  }

  class TestDo {
    public String doSome(String input) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String output = input + ":" + System.currentTimeMillis() / 1000;
      return output;
    }
  }
}
