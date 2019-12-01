package day07.code_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {

    public static void main(String[] args) {
        //常量值为100
        final int THREADS = 100;
        //创建一个容量为1000的原子数组
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);
        //创建增、降两个对象
        Incrementer incrementer = new Incrementer(vector);
        Decrementer decrementer = new Decrementer(vector);
        //创建两个数组
        Thread[] incrementerThreads = new Thread[THREADS];
        Thread[] decrementerThreads = new Thread[THREADS];
        //填充数组并开启线程
        for (int i = 0; i < THREADS; i++) {
            incrementerThreads[i] = new Thread(incrementer);
            decrementerThreads[i] = new Thread(decrementer);
            incrementerThreads[i].start();
            decrementerThreads[i].start();
        }
        //等待所有线程执行结束
        for (int i = 0; i < THREADS; i++) {
            try {
                incrementerThreads[i].join();
                decrementerThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //检查错误的结果并打印
        for (int i = 0; i < vector.length(); i++) {
            if (vector.get(i) != 0) {
                System.out.printf("Vector[%d] : %d", i, vector.get(i));
            }
        }
        //程序结束提示语
        System.out.println("Main: End of the example");
    }

}
