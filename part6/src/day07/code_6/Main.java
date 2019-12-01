package day07.code_6;


import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {
        //创建线程数组
        Thread[] threads = new Thread[3];
        //创建并开启三个线程
        for (int i = 0; i < threads.length; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }

    }

}
