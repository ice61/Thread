package day07.code_1;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    public static void main(String[] args) {
        //创建一个列表
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        //创建一个线程数组
        Thread[] threads = new Thread[100];
        //创建100个加入数据的线程并启动
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        //打印线程启动提示信息
        System.out.printf("Main: %d AddTask threads have been launched\n",
                threads.length);
        //等待加入数据的线程运行结束
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印列表中当前的数据量
        System.out.printf("Main: Size of the List: %d\n", list.size());
        //创建100个取出数据的线程并启动
        for (int i = 0; i < threads.length; i++) {
            PollTask task = new PollTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        //打印线程启动提示信息
        System.out.printf("Main: %d PollTask threads have been launched\n",
                threads.length);
        //等待取出数据的线程运行结束
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印列表中当前的数据量
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }

}
