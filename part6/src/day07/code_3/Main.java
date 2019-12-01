package day07.code_3;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args) {
        //创建列表
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        //创建线程数组
        Thread[] threads = new Thread[5];
        //初始化数组并启动线程
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i, queue);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        //等待所有线程执行完毕
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印列表的容量
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        //取出所有事件
        for (int i = 0; i < threads.length * 1000; i++) {
            Event event = queue.poll();
            //打印取出事件的线程编号和优先级
            System.out.printf("Thread %s: Priority %d\n",
                    event.getThread(), event.getPriority());
        }
        //打印列表容量
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        //打印程序运行结束提示语
        System.out.println("Main: End of the program");
    }

}
