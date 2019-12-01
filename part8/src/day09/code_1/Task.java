package day09.code_1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task implements Runnable {

    private Lock lock;

    //通过构造方法初始化锁
    public Task(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        //循环5次
        for (int i = 0; i < 5; i++) {
            //获取锁
            lock.lock();
            //打印当前线程持有锁的信息
            System.out.printf("%s: Get the Lock\n",
                    Thread.currentThread().getName());
            try {
                //休眠500毫秒
                Thread.sleep(500);
                //打印当前线程释放锁的信息
                System.out.printf("%s: Free the Lock\n",
                        Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }
}
