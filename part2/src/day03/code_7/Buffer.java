package day03.code_7;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    //用来装载数据的集合
    private LinkedList<String> buffer;

    //数据缓冲器的最大容量
    private int maxSize;

    //用来同步代码块的锁
    private ReentrantLock lock;

    //用来判断缓冲器中是否有数据的条件
    private Condition lines;

    //用来判断缓冲器中是否为空的条件
    private Condition space;

    //用来判断文件是否还有未读的数据
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        //用锁创建两个条件
        lines = lock.newCondition();
        space = lock.newCondition();
        //缓冲器将来会有数据，所以置为true
        pendingLines = true;
    }

    //向缓冲器中添加数据的方法
    public void insert(String line) {
        //加锁
        lock.lock();
        try {
            //如果缓冲器已满，就使用space条件让当前线程挂起
            while (buffer.size() == maxSize) {
                space.await();
            }
            //向缓冲器末尾添加一条数据并打印相应信息
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n",
                    Thread.currentThread().getName(),
                    buffer.size());
            //因为插入了数据，所以使用lines条件唤起因数据为空而休眠的消费者
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //从缓冲器中取数据的方法
    public String get() {
        String line = null;
        //加锁
        lock.lock();
        try {
            //如果现在缓冲器无数据但将来会有则休眠
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }
            //经过上面的判断，此处判断如果返回为true则缓冲器中一定存在数据
            if (hasPendingLines()) {
                //获取并删除列表的第一个元素
                line = buffer.poll();
                //打印相关信息
                System.out.printf("%s: Line Readed: %d\n",
                        Thread.currentThread().getName(),
                        buffer.size());
                //消耗了一条数据所以使用space条件唤醒因缓冲器满而休眠的生产者
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
        //返回取出的数据
        return line;
    }

    /*
     * 设置文件是否已经读空
     * 生产者线程在读空文件中的所有数据后将调用此方法将pendingLines置为true
     * */
    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    //此方法表示现在或将来缓冲器中是否会有数据
    public boolean hasPendingLines() {
        /*
        * pendingLines表示文件是否已经读空
        * buffer.size()表示缓冲器中的容量
        * 只有当文件已空且缓冲器中不存在数据时才返回false
        * 返回false表示现在和将来都不会再有数据在缓冲器中
        * */
        return pendingLines || buffer.size() > 0;
    }


}
