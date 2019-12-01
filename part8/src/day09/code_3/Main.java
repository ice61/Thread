package day09.code_3;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建一个缓存线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor)
                Executors.newCachedThreadPool();
        //创建随机数生成器
        Random random = new Random();
        //循环十次
        for (int i = 0; i < 10; i++) {
            //创建任务并将随机数作为其休眠时间
            Task task = new Task(random.nextInt(10000));
            //向执行器发送任务
            executor.submit(task);
        }
        //循环5次
        for (int i = 0; i < 5; i++) {
            //调用showLog方法打印线程池的信息
            showLog(executor);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //关闭线程池
        executor.shutdown();
        //循环5次
        for (int i = 0; i < 5; i++) {
            //调用showLog方法打印线程池的信息
            showLog(executor);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //等待线程池执行完所有任务后关闭
        executor.awaitTermination(1, TimeUnit.DAYS);
        //打印程序结束提示信息
        System.out.printf("Main: End of the program\n");
    }

    private static void showLog(ThreadPoolExecutor executor) {
        //准备打印线程池相关信息
        System.out.printf("***********************\n");
        System.out.printf("Main: Executor Log\n");
        //打印线程池的核心线程数
        System.out.printf("Main: Executor: Core Pool Size: %d\n",
                executor.getCorePoolSize());
        //打印线程池的实际线程数
        System.out.printf("Main: Executor: Pool Size: %d\n",
                executor.getPoolSize());
        //打印线程池中正在执行任务的线程数
        System.out.printf("Main: Executor: Active Count: %d\n",
                executor.getActiveCount());
        //打印提交的任务数（计划执行的任务数）
        System.out.printf("Main: Executor: Task Count: %d\n",
                executor.getTaskCount());
        //打印执行器已执行完成的线程数
        System.out.printf("Main: Executor: Completed Task Count: %d\n",
                executor.getCompletedTaskCount());
        //打印执行器是否关闭
        System.out.printf("Main: Executor: Shutdown: %s\n",
                executor.isShutdown());
        //打印执行器是否正在终止
        System.out.printf("Main: Executor: Terminating: %s\n",
                executor.isTerminating());
        //打印执行器是否已经终止
        System.out.printf("Main: Executor: Terminated: %s\n",
                executor.isTerminated());
        System.out.printf("***********************\n");
    }

}
