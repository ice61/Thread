package day08.code_06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建定制线程工厂
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
        //创建线程池并将定制线程工厂作为参数传入
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);
        //创建超大数组并初始化
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        //创建任务
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
        //异步执行
        pool.execute(task);
        //等待任务执行结束
        task.join();
        //关闭线程池
        pool.shutdown();
        //等待线程池中的任务执行结束
        pool.awaitTermination(1, TimeUnit.DAYS);
        //打印任务返回的结果和程序执行结束提示语
        System.out.printf("Main: Result: %d\n", task.get());
        System.out.println("Main: End of the program");
    }

}
