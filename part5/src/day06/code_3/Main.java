package day06.code_3;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //创建三个任务并异步执行
        FolderProcessor system = new FolderProcessor("C:\\", "exe");
        FolderProcessor program = new FolderProcessor("D:\\", "exe");
        FolderProcessor data = new FolderProcessor("F:\\", "exe");
        pool.execute(system);
        pool.execute(program);
        pool.execute(data);
        //在任务没有都结束之前不断循环打印线程池的信息
        do {
            System.out.println("***************************************");
            System.out.printf("Main: Parallelism: %d\n",
                    pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n",
                    pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",
                    pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",
                    pool.getStealCount());
            System.out.println("***************************************");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((!system.isDone()) || (!program.isDone()) || (!data.isDone()));
        //关闭线程池
        pool.shutdown();
        //获取并打印每一个任务返回的结果
        List<String> result;
        result = system.join();
        System.out.printf("System: %d files found\n", result.size());
        result = program.join();
        System.out.printf("Program: %d files found\n", result.size());
        result = data.join();
        System.out.printf("Data: %d files found\n", result.size());
    }

}
