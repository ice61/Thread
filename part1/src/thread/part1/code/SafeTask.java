package thread.part1.code;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {

    //线程局部变量
    private ThreadLocal<Date> startDate = new ThreadLocal<>();

    @Override
    public void run() {
        //为线程局部变量设置日期
        startDate.set(new Date());
        //在休眠前打印日期并标明线程id
        System.out.printf("Starting Thread: %s: %s\n",
                Thread.currentThread().getId(), startDate.get());
        try {
            //休眠，给其他线程设置创建日期的时间
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //休眠后再次打印创建日期，观察三个线程的创建日期是否一样
        System.out.printf("Thread Finished : %s: %s\n",
                Thread.currentThread().getId(), startDate.get());
    }

    public static void main(String[] args) {
        SafeTask safeTask = new SafeTask();
        //创建三个线程，每创建一个休眠两秒
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(safeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
