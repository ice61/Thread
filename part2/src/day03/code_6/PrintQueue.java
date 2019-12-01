package day03.code_6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue extends day03.code_4.PrintQueue {
    private Lock queueLock = new ReentrantLock(true);

    public void printJob() {
        try {
            //第一次加锁
            queueLock.lock();
            //打印
            long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()
                    + ":PrintQueue1: Printing a Job during "  +
                    (duration/1000) + " seconds");
            //休眠
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //第一次释放锁
            queueLock.unlock();
        }
        try {
            //第二次加锁
            queueLock.lock();
            //打印
            long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()
                    + ":PrintQueue2: Printing a Job during "  +
                    (duration/1000) + " seconds");
            //休眠
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //第二次释放锁
            queueLock.unlock();
        }
    }
}
