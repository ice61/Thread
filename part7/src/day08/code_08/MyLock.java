package day08.code_08;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock implements Lock {

    //定制AQS类对象
    private AbstractQueuedSynchronizer sync;

    public MyLock() {
        //通过构造方法为AQS对象赋值
        sync = new MyAbstractQueuedSynchronizer();
    }

    @Override
    public void lock() {
        //调用AQS类的方法尝试修改计数器的值
        //此方法内部会调用定制AQS类中的tryAcquire方法
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //调用AQS类的方法尝试修改计数器的值(可中断)
        //此方法内部会调用定制AQS类中的tryAcquire方法
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        //尝试获取锁，如果失败直接返回不阻塞
        try {
            return sync.tryAcquireNanos(1, 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        //尝试在指定时间内获取锁，如果失败直接返回不阻塞
        return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
    }

    @Override
    public void unlock() {
        //调用AQS类的方法尝试减少计数器的值
        //此方法内部会调用定制AQS类的tryRelease方法
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        //创建AQS内部类对象并返回
        return sync.new ConditionObject();
    }
}
