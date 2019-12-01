package day07.code_3;

import java.util.concurrent.PriorityBlockingQueue;

public class Task implements Runnable {

    //任务编号
    private int id;

    //列表
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        //将1000个事件加入列表
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }
}
