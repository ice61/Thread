package day08.code_05;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建我们自己的定时执行器
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
        //创建一个任务
        Task task = new Task();
        //打印程序开始的时间
        System.out.printf("Main: %s\n", new Date());
        //按照指定时间执行任务
        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
        //当前线程休眠10秒
        TimeUnit.SECONDS.sleep(10);
        //关闭执行器
        executor.shutdown();
        //等待执行器关闭
        executor.awaitTermination(1, TimeUnit.DAYS);
        //打印程序结束提示语
        System.out.printf("Main: End of the program\n");
    }

}
