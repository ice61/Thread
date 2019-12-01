package thread.part1.code;

import java.util.concurrent.TimeUnit;

public class Product implements Runnable {

    @Override
    public void run() {
        //这个类的任务只是休眠一秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建线程工厂
        MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
        //创建产品对象
        Product product = new Product();
        Thread thread;
        System.out.println("Starting the Threads");
        for (int i = 0; i < 10; i++) {
            //使用线程工厂对象循环创建十个线程对象并执行
            thread = threadFactory.newThread(product);
            thread.start();
        }
        //打印工厂的状态
        System.out.println("Factory stats:");
        System.out.printf("%s\n",threadFactory.getStats());
    }

}
