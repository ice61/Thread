package day04.code_1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PrintQueue {

    //信号量对象的声明
    private final Semaphore semaphore;

    //通过构造函数初始化信号量
    public PrintQueue() {
        //通过构造函数为信号量的计数器赋值
        semaphore = new Semaphore(1);
    }

    public void printJob() {
        try {
            //获得信号量，此方法会抛出异常
            semaphore.acquire();
            //打印相关信息后休眠随机的时间
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue1: Printing a Job during " +
                    "%d seconds\n", Thread.currentThread().getName(), duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放信号量
            semaphore.release();
        }
    }
}
