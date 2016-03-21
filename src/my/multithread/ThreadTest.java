package my.multithread;

/**
 * Created by nara007 on 16/3/16.
 */
public class ThreadTest {
  boolean isSubThread = true;

  public synchronized void mainThread(int j) {
    if (isSubThread) {
      try {
        System.out.printf("xiumian   qian ***** %d\n", j);
        this.wait();
        System.out.printf("xiumian   hou.....%d\n", j);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // for (int i = 0; i < 10; i++) {
    // System.out.printf("main thread... loop %d, di %d ci\n", j, i);
    // }
//else{
    System.out.printf("main thread... loop       %d, \n", j);
    isSubThread = true;
    this.notify();
//    }
  }

  public synchronized void subThread(int j) {
    if (!isSubThread) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    // for (int i = 0; i < 10; i++) {
    // System.out.printf("sub thread...  loop %d  di %d ci\n", j, i);
    // }
//else {
      System.out.printf("sub thread...  loop %d  \n", j);

      isSubThread = false;
      this.notify();
//    }
  }


  public static void main(String[] args) {


    ThreadTest threadTest = new ThreadTest();

    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 50; i++)
          threadTest.subThread(i);
      }
    }.start();




    for (int i = 0; i < 50; i++) {
      threadTest.mainThread(i);
    }



  }
}
