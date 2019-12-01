package day05.code_07;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //打印程序开始的时间
        System.out.printf("Main: Starting at: %s\n", new Date());
        //创建任务
        Task task = new Task("Task");
        //创建一个定时执行器
        ScheduledThreadPoolExecutor executor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        //定时执行任务
        ScheduledFuture<?> result =
                executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            //打印距离下一次执行任务的时间
            System.out.printf("Main: Delay: %d\n",
                    result.getDelay(TimeUnit.MILLISECONDS));
            //休眠0.5秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //关闭执行器
        executor.shutdown();
    }

}
