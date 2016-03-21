package my.multithread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by nara007 on 16/3/18.
 */
public class LockTest {

  Map<String, String> myMap = new HashMap<String, String>();
  volatile boolean cacheValid;
  ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

  public String getCache(String key) {


    return null;

  }

  public static void main(String[] args) {

  }

}
