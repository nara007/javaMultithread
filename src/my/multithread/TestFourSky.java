package my.multithread;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by nara007 on 16/3/21.
 */
public class TestFourSky extends Thread {

  private TestDo testDo;
  private String key;
  private String value;
  public static CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();

  public TestFourSky(String key, String key2, String value) {

    this.testDo = this.getInstance();
    this.key = key + key2;
    this.value = value;

    Iterator<String> iterator = TestFourSky.set.iterator();
    while(iterator.hasNext())
    {
      String tmpKey = iterator.next();
      if (this.key.equals(tmpKey))
      {
        this.key = tmpKey;
        break;
      }
    }
    TestFourSky.set.add(this.key);
  }

  @Override
  public void run() {
    synchronized (this.key) {
      this.testDo.doSome(this.key, this.value);
    }
  }

  public static void main(String[] args) {

    TestFourSky a = new TestFourSky("1", "", "1");
    TestFourSky b = new TestFourSky("1", "", "2");
    TestFourSky c = new TestFourSky("3", "", "3");
    TestFourSky d = new TestFourSky("4", "", "4");

    a.start();
    b.start();
    c.start();
    d.start();

//    CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
//
//    String substr1 = new String("12");
//    String substr2 = "3";
//    String str1 = substr1+substr2;
//    String str2 = new String("123");
//    set.add(str1);
//    set.add(str2);
//
//    System.out.println(set.size());

  }


  public TestDo getInstance() {
    return new TestDo();
  }

  class TestDo {


    public void doSome(Object key, String value) {
      {
        try {
          Thread.sleep(1000);
          System.out.println(key + ":" + value + ":" + System.currentTimeMillis() / 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }
}
