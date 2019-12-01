package day04.code_3;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {


    private final CountDownLatch controller;

    //通过构造函数创建CountDownLatch对象
    public Videoconference(int number) {
        //number为需要等待的线程数量
        controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        //在会议类刚运行时打印需要到会的总人数
        System.out.printf("VideoConference: Initialization: %d participants\n",
                controller.getCount());
        try {
            //等待与会人员到达
            controller.await();
            //所有线程均到达，会议开始
            System.out.printf("VideoConference: All the participants have come\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //与会者到达方法，name为与会者姓名
    public void arrive(String name) {
        //打印与会者到达的信息
        System.out.printf("%s has arrived.\n", name);
        //调用countDown方法使内置计数器减1
        controller.countDown();
        //打印未到会人数
        System.out.printf("Waiting for %d participants\n",
                controller.getCount());
    }
}
