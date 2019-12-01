package day05.code_01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    //任务创建的时间
    private Date initDate;

    //任务名称
    private String name;

    public Task(String name) {
        initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        //打印任务的创建时间
        System.out.printf("%s: Task %s: Created on: %s\n",
                Thread.currentThread().getName(), name, initDate);
        //打印任务的开始执行时间
        System.out.printf("%s: Task %s: Started on: %s\n",
                Thread.currentThread().getName(), name, new Date());
        //休眠一段时间并打印休眠时间
        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n",
                    Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印任务的结束时间
        System.out.printf("%s: Task %s: Finished on: %s\n",
                Thread.currentThread().getName(), name, new Date());
    }
}
