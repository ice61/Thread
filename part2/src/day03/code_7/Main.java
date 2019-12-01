package day03.code_7;

public class Main {

    public static void main(String[] args) {
        //创建模拟文件对象
        FileMock mock = new FileMock(100, 10);
        //创建字符缓冲器
        Buffer buffer = new Buffer(20);
        //创建生产者并将其作为参数传入线程对象的构造方法中
        Producer producer = new Producer(mock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");
        //开启生产者线程
        threadProducer.start();
        //线程休眠2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建消费者对象
        Consumer consumer = new Consumer(buffer);
        //创建三个消费者线程并启动
        for (int i = 0; i < 3; i++) {
            Thread threadConsumer = new Thread(consumer, "Consumer" + i);
            threadConsumer.start();
        }
    }

}
