package day06.code_4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建数组
        int[] array = new int[100];
        //创建任务
        Task task = new Task(array, 0, 100);
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //执行任务
        pool.execute(task);
        //关闭线程池
        pool.shutdown();
        //休眠，直至线程池中的任务全部完成
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断任务是否存在异常
        if (task.isCompletedAbnormally()) {
            //打印异常提示语
            System.out.printf("Main: An exception has ocurred\n");
            //打印获取到的异常对象
            System.out.printf("Main: %s\n", task.getException());
        }
        //打印任务结果
        System.out.printf("Main: Result: %d", task.join());
    }

}
