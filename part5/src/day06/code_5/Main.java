package day06.code_5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建数组生成器
        ArrayGenerator generator = new ArrayGenerator();
        //得到一个容量为1000的数组
        int[] array = generator.generateArray(1000);
        //创建任务管理器
        TaskManager manager = new TaskManager();
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //创建搜素数字任务
        SearchNumberTask task = new SearchNumberTask
                (array, 0, 1000, 5, manager);
        //将任务发送给线程池执行
        pool.execute(task);
        //关闭线程池
        pool.shutdown();
        //等待线程池将所有未取消的任务执行完毕
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印程序结束信息
        System.out.println("Main: The program has finished");
    }

}
