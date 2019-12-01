package day09.code_1;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends ReentrantLock {

    //获取当前持有锁的线程的名称
    public String getOwnerName() {
        //判断当前是否有线程持有锁
        if (getOwner() == null) {
            return "None";
        }
        //获得当前持有锁的线程的名称
        return getOwner().getName();
    }

    public Collection<Thread> getThreads() {
        //返回当前等待获取锁的线程集合
        return getQueuedThreads();
    }


}
