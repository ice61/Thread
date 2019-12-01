package day03.code_4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//打印队列类
public class PrintQueue {

    //创建一个锁对象用于接下来的同步
    private final Lock queueLock = new ReentrantLock();

    public void printJob() {
        //获取锁，以下代码被同步
        queueLock.lock();
        /*
         * 使用随机数生成一个值
         * 打印当前线程的名字和打印（休眠）所需要的时间
         * 模拟打印（进入休眠）
         * */
        long duration = (long) (Math.random() * 10000);
        System.out.println(Thread.currentThread().getName()
                + ":PrintQueue: Printing a Job during " +
                (duration / 1000) + " seconds");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //千万不要忘记释放锁，以上代码被同步
            queueLock.unlock();
        }
    }
}
