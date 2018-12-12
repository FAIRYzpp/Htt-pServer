package thread;

public class Actor extends Thread {

    public void run() {
        System.out.println(getName() + "开始");
        int count = 0;
        boolean keeprunner = true;
        while (keeprunner) {
            System.out.println(getName() + "是第" + (++count) + "个线程");

            if (count == 100) {
                keeprunner = false;
            }

            if (count % 10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(getName() + "线程结束");
    }
    public static void main(String args[]){
        Thread acter = new Actor();
        acter.setName("MyThread");
        acter.start();

        Thread actress = new Thread(new Actress(),"MyRunnable");
        actress.start();
    }
}

class Actress implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始");
        int count = 0;
        boolean keeprunner = true;
        while (keeprunner) {
            System.out.println(Thread.currentThread().getName() + "是第" + (++count) + "个线程");

            if (count == 100) {
                keeprunner = false;
            }

            if (count % 10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }
}

