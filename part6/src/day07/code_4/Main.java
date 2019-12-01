package day07.code_4;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Main {

    public static void main(String[] args) {
        //创建列表
        DelayQueue<Event> queue = new DelayQueue<>();
        //创建线程并启动
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i + 1, queue);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        //等待线程运行结束
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //读取事件直到列表为空
        do {
            //初始化计数器
            int counter = 0;
            Event event;
            //从列表中不断取出事件直到没有可用事件
            do {
                event = queue.poll();
                //如果事件不为null，计数器增加
                if (event != null) {
                    counter++;
                }
            } while (event != null);
            //打印已读取的事件个数
            System.out.printf("At %s you have read %d events\n",
                    new Date(), counter);
            //休眠500毫秒后继续读取
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (queue.size() > 0);
    }

}
