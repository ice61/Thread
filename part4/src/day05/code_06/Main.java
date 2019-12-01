package day05.code_06;

import java.util.Date;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        //创建一个定时执行器并设定基本线程量为1
        ScheduledThreadPoolExecutor executor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        //打印程序开始提示信息
        System.out.printf("Main: Starting at: %s\n", new Date());
        //创建5个任务并延时执行
        for (int i = 0; i < 5; i++) {
            Task task = new Task("Task " + i);
            //使用执行器延时执行此任务
            executor.schedule(task, i + 1, TimeUnit.SECONDS);
        }
        //关闭执行器
        executor.shutdown();
        //当前线程阻塞直到执行器执行完所有已提交的任务
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印程序结束信息
        System.out.printf("Main: Ends at: %s\n", new Date());
    }

}
