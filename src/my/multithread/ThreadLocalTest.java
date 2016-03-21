package my.multithread;

import java.util.concurrent.*;

/**
 * Created by nara007 on 16/3/17.
 */
public class ThreadLocalTest {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> future = executorService.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(2000);
        return "hello";
      }
    });

    System.out.println("等待结果。。。");
    try {
      System.out.printf("futhre is %s\n", future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("这里是结尾输出");

  }
}
