package thread.part1.code;

import java.io.*;
import java.util.ArrayList;

public class Calculator2 implements Runnable {

    private int number;

    public Calculator2(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s : %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
    }

    //此方法用于在线程对象的状态发生变化时将相应信息写入指定的文件中
    public static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\n",thread.getPriority());
        pw.printf("Main : Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.println("*********************************************");
    }

    public static void main(String[] args) {
        //创建一个容量为10的线程数组用来存放线程
        Thread[] threads = new Thread[10];
        //创建一个容量为10的线程状态数组用来存放线程状态
        Thread.State[] states = new Thread.State[10];
        //创建10个Calculator2，并通过构造方法传入Thread，设置不同的优先级
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator2(i));
            if ((i%2) == 0) {
                //设置线程的优先级，1最小，10最大
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            //设置线程的名字
            threads[i].setName("Thread" + i);
        }

        //创建PrintWriter对象便于将线程状态的变化写入文件中
        try {
            FileOutputStream file = new FileOutputStream("src/thread/part1/log/Calculator2Log.txt");
            PrintWriter pw = new PrintWriter(file);

            //因当前线程还未启动过，所以状态是new
            for(int i = 0;i<10;i++) {
                pw.println("Main : Status of Thread" + i + " : " + threads[i].getState());
                states[i] = threads[i].getState();
            }
            //启动所有线程
            for(int i = 0;i<10;i++) {
                threads[i].start();
            }
            //监控每个线程的状态以此来决定是否结束程序
            boolean finish = false;
            while (!finish) {
                //循环遍历状态数组来判断状态是否改变，如果改变就写入文件
                for(int i =0;i<10;i++) {
                    if (threads[i].getState() != states[i]) {
                        writeThreadInfo(pw,threads[i],states[i]);
                        states[i] = threads[i].getState();
                    }
                }
                /*
                * 遍历一次之后，将finish置为true
                * 判断所有线程是否都已经结束
                * 两者相与表示同时成立后值给finish，以此来决定是否结束
                * */
                finish = true;
                for(int i = 0;i<10;i++) {
                    finish = finish && threads[i].getState() == Thread.State.TERMINATED;
                }
            }
            //没有此条语句的话缓冲区内的字符不会被刷到文件中，可能会导致信息未写入文件或写入不全
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
