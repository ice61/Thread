package day03.code_3;

public class Main {

    public static void main(String[] args) {
        //创建存储空间对象
        EventStorage eventStorage = new EventStorage();
        /*
         * 创建生产者对象作为参数传入线程对象的构造函数中
         * 创建消费者对象作为参数传入线程对象的构造函数中
         * */
        Producer producer = new Producer(eventStorage);
        Thread thread1 = new Thread(producer, "producer");
        Thread thread2 = new Thread(producer, "producer");
        Thread thread3 = new Thread(producer, "producer");
        Consumer consumer = new Consumer(eventStorage);
        Thread thread4 = new Thread(consumer, "consumer");
        Thread thread5 = new Thread(consumer, "consumer");
        //开启线程
        thread1.start();
        thread2.start();
        thread3.start();

        thread4.start();
        thread5.start();
    }

}
