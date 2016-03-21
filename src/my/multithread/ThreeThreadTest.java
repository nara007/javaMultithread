package my.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nara007 on 16/3/19.
 */
public class ThreeThreadTest {
  private Lock lock = new ReentrantLock();
  private Condition conditionA = lock.newCondition();
  private Condition conditionB = lock.newCondition();
  private Condition conditionC = lock.newCondition();


  public enum ThreadNum {
    threadOne, threadTwo, threadThree;

    public static ThreadNum valueOf(int ordinal) {
      if (ordinal < 0 || ordinal >= values().length) {
        throw new IndexOutOfBoundsException("Invalid ordinal");
      }
      return values()[ordinal];
    }

  }

  private ThreadNum currentThread = ThreadNum.threadOne;

  public void threadOne(int i) {
    lock.lock();

    try {
      while (currentThread != ThreadNum.threadOne) {
        conditionA.await();
      }

      System.out.println("this is thread one " + i);
      currentThread = ThreadNum.valueOf((currentThread.ordinal() + 1) % 3);
      conditionB.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

  }

  public void threadTwo(int i) {
    lock.lock();
    try {
      while (currentThread != ThreadNum.threadTwo) {
        conditionB.await();
      }

      System.out.println("this is thread two " + i);
      currentThread = ThreadNum.valueOf((currentThread.ordinal() + 1) % 3);
      conditionC.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void threadThree(int i) {
    lock.lock();
    try {
      while (currentThread != ThreadNum.threadThree) {
        conditionC.await();
      }

      System.out.println("this is thread three " + i);
      currentThread = ThreadNum.valueOf((currentThread.ordinal() + 1) % 3);
      conditionA.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }


  public static void main(String[] args) {

    ThreeThreadTest threeThreadTest = new ThreeThreadTest();

    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          threeThreadTest.threadOne(i);
        }
      }
    }.start();

    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          threeThreadTest.threadTwo(i);
        }
      }
    }.start();

    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          threeThreadTest.threadThree(i);
        }
      }
    }.start();

    // for (int i = 0; i < 10; i++) {
    // threeThreadTest.threadThree(i);
    // }

    // while (true) {
    // try {
    // Thread.sleep(1000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    //
    // System.out.println("main thread ...");
    // }

  }
}
