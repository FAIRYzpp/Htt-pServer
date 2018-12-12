package thread;

import httpservice.HttpServer;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static ExecutorService executor = new ThreadPoolExecutor(1000,1000,60, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>(1000));
    //只有一个线程的线程池--SingleThreadExecutor
    public static ExecutorService singThreadExecutor = Executors.newSingleThreadExecutor();

    //有固定线程的线程池--FixedThreadPool
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    //大小不限的线程池--CachedThreadPool
    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String args[]){
        for (int i = 0;i<1000;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println(Thread.currentThread().getName());
        }
        executor.shutdown();
    }
}

class MyTask implements Runnable{
    private int taskNum;
    public MyTask(int num){
        this.taskNum = num;
    }
    @Override
    public void run() {
        System.out.println("正在执行task"+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task"+taskNum+"执行完毕");
    }
}
