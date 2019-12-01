package day04.code_2;

import day04.code_1.Job;
import day04.code_1.PrintQueue;

public class Main {

    public static void main(String[] args) {
        //创建改进版打印队列
        PrintQueue1 printQueue1 = new PrintQueue1();
        //创建工作类对象，并传入打印队列对象引用
        Job job = new Job(printQueue1);
        //以工作类对象为参数创建十个线程并启动
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(job);
            thread.start();
        }
    }

}
