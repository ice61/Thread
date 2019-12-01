package day04.code_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        //为生产者、消费者各创建一个容器
        ArrayList<String> buffer1 = new ArrayList<>();
        ArrayList<String> buffer2 = new ArrayList<>();

        //创建一个交换器
        Exchanger<List<String>> exchanger = new Exchanger<>();

        //创建生产者并作为参数传入线程构造函数，启动线程
        Producer producer = new Producer(buffer1, exchanger);
        Thread thread1 = new Thread(producer);
        thread1.start();

        //创建消费者并作为参数传入线程构造函数，启动线程
        Consumer consumer = new Consumer(buffer2, exchanger);
        Thread thread2 = new Thread(consumer);
        thread2.start();
    }

}
