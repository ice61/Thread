package thread.part1.code;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {

    //用来装载事件的容器，是一个双端队列，线程不安全类
    private Deque<Event> deque;

    //构造方法，在这里我们通过setDaemon方法将线程设置为守护线程
    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        /*
        * 设置死循环来让守护线程始终运行
        * 创建一个日期类型并传入clean方法中用于比较
        * */
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    //clean方法用于清除容器中的过期事件
    private void clean(Date date) {
        //存储时间差
        long difference;
        //是否删除
        boolean delete;
        //因为deque是线程不安全类，所以我们在这里加锁，否则会出现错误
        synchronized (deque) {
            //如果容器为空，直接返回
            if (deque.size() == 0) {
                return;
            }
            //初始化
            delete = false;
            do {
                //从队尾取出一个事件
                Event e = deque.getLast();
                //取出事件的创建时间并作差
                difference = date.getTime() - e.getDate().getTime();
                //如果时间差大于10s，删除事件、打印语句、delete置true
                if (difference > 10000) {
                    System.out.printf("Cleaner: %s\n", e.getEvent());
                    deque.removeLast();
                    delete = true;
                }
            } while (difference > 10000);
            //如果删除了事件，则打印容器当前的大小
            if (delete) {
                System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
            }
        }

    }
}
