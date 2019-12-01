package day08.code_08;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAbstractQueuedSynchronizer extends
        AbstractQueuedSynchronizer {

    //采用原子变量作为内部计数器
    private volatile AtomicInteger state;

    public MyAbstractQueuedSynchronizer() {
        //在构造方法中初始化计数器
        state = new AtomicInteger(0);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        //获得当前线程
        Thread now = Thread.currentThread();
        //判断当前线程是否为持锁线程
        if (getExclusiveOwnerThread() == now) {
            //增加计数器的值
            state.set(state.get() + arg);
            return true;
            //否则尝试增大计数器的值
        } else if (state.compareAndSet(0, arg)) {
            //修改成功，设置当前线程为持锁线程
            setExclusiveOwnerThread(now);
            return true;
        }
        //修改失败返回false
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        //获得当前线程
        Thread now = Thread.currentThread();
        //当前线程不是持锁线程就直接抛异常
        if (now != getExclusiveOwnerThread()) {
            throw new RuntimeException("Error!");
        }
        //得到计数器当前值
        int number = state.get();
        //判断减少指定参数后是否为0
        if (number - arg == 0) {
            //为0则表示线程释放了锁，将持锁线程设置为null
            setExclusiveOwnerThread(null);
        }
        //减少计数器的值
        return state.compareAndSet(number, number - arg);
    }
}
