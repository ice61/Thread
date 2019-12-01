package day09.code_1;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建锁对象
        MyLock lock = new MyLock();
        //创建线程数组
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            //创建任务并将锁传入
            Task task = new Task(lock);
            //创建线程
            threads[i] = new Thread(task);
            //开启线程
            threads[i].start();
        }
        //循环15次
        for (int i = 0; i < 15; i++) {
            //打印提示信息
            System.out.printf("Main: Logging the Lock\n");
            System.out.printf("**************************\n");
            //打印当前持有锁的线程名称
            System.out.printf("Lock: Owner : %s\n", lock.getOwnerName());
            //打印当前是否有线程正等待获取锁
            System.out.printf("Lock: Queued Threads: %s\n",
                    lock.hasQueuedThreads());
            //如果存在线程等待获取锁
            if (lock.hasQueuedThreads()) {
                //打印等待获取锁的线程的数量
                System.out.printf("Lock: Queue Length: %d\n",
                        lock.getQueueLength());
                //提示信息前缀
                System.out.printf("Lock: Queued Threads: ");
                //获取等待获取锁的线程集合
                Collection<Thread> lockedThreads = lock.getThreads();
                //遍历集合打印线程的名称
                for (Thread lockedThread : lockedThreads) {
                    System.out.printf("%s ", lockedThread.getName());
                }
                //换行
                System.out.printf("\n");
            }
            //打印锁的公平性
            System.out.printf("Lock: Fairness: %s\n", lock.isFair());
            //打印锁是否被某个线程持有
            System.out.printf("Lock: Locked: %s\n", lock.isLocked());
            System.out.printf("**************************\n");
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
