package day05.code_11;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        //打印任务开始信息
        System.out.println("Task " + name + " Starting");
        //休眠
        long duration = (long) (Math.random() * 10);
        System.out.printf("Sleeping %d seconds\n", duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印任务结束信息
        System.out.printf("Task %s: Ending\n", name);
    }

}
