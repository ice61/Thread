package day08.code_08;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    //定制锁
    private MyLock lock;

    //任务名曾
    private String name;

    public Task(MyLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        //获取锁
        lock.lock();
        //打印获取锁的提示信息
        System.out.printf("Task: %s: Take the lock\n", name);
        //调用hello方法，主要为了测试定制锁的可重入性
        hello();
        //休眠两秒
        try {
            TimeUnit.SECONDS.sleep(2);
            //打印释放锁的提示信息
            System.out.printf("Task: %s: Free the lock\n", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    private void hello() {
        //获取锁
        lock.lock();
        //打印Hello
        System.out.println("Hello!");
        //释放锁
        lock.unlock();
    }
}
