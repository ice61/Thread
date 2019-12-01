package day07.code_4;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Task implements Runnable {

    //任务编号
    private int id;

    //列表
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        //当前时间
        Date now = new Date();
        //推迟的时间
        Date delay = new Date();
        //设置推迟时间
        delay.setTime(now.getTime() + id * 1000);
        //打印线程信息和推迟的时间
        System.out.printf("Thread %s: %s\n", id, delay);
        //将事件装入列表中
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
