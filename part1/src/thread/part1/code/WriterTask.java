package thread.part1.code;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriterTask implements Runnable {

    //用来装载事件的容器，是一个双端队列，线程不安全类
    private Deque<Event> deque;

    //有参构造方法
    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        //循环创建100个事件
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            //设置事件创建时间和时间内容
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));
            //加锁，向队列头添加刚创建好的事件
            synchronized (deque) {
                deque.addFirst(event);
            }
            try {
                //休眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建一个容器并作为参数传给构造函数
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        //创建三个用户线程并运行
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask,"write");
            thread.start();
        }
        //创建守护线程并运行
        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();
    }
}
