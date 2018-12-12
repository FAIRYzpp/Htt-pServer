package thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static final Logger logger = LoggerFactory.getLogger(TestRunnable.class);
    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0;i<15;i++){
            executorService.execute(new TestRunnable());
            System.out.println("*************"+i);
        }
        executorService.shutdown();
    }
}

class TestRunnable extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(TestRunnable.class);
    @Override
    public void run(){
        try {
            Thread.sleep(1000*6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName()+"-线程被调用了");
    }

}
