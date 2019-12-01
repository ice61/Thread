package day09.code_4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //创建数组
        int[] array = new int[10000];
        //创建任务
        Task task = new Task(array, 0, 10000);
        //异步执行
        pool.execute(task);
        //在任务执行结束前不断循环
        while (!task.isDone()) {
            //打印线程池的信息
            showLog(pool);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //关闭线程池
        pool.shutdown();
        //等待线程池执行完所有任务
        pool.awaitTermination(1, TimeUnit.DAYS);
        //打印线程池信息
        showLog(pool);
        //打印程序结束提示信息
        System.out.printf("Main: End of the program\n");
    }

    private static void showLog(ForkJoinPool pool) {
        //打印线程池提示信息
        System.out.printf("***********************\n");
        System.out.printf("Main: Fork/Join Pool log\n");
        //打印线程池并行级数
        System.out.printf("Main: Fork/Join Pool: Parallelism: %d\n",
                pool.getParallelism());
        //打印线程池内部实际线程数量
        System.out.printf("Main: Fork/Join Pool: Pool Size: %d\n",
                pool.getPoolSize());
        //打印正在执行任务的线程数
        System.out.printf("Main: Fork/Join Pool: Active Thread Count: %d\n",
                pool.getActiveThreadCount());
        //打印正在工作且未被阻塞的线程数
        System.out.printf("Main: Fork/Join Pool: Running Thread Count: %d\n",
                pool.getRunningThreadCount());
        //打印已提交但未被执行过的任务数
        System.out.printf("Main: Fork/Join Pool: Queued Submission: %d\n",
                pool.getQueuedSubmissionCount());
        //打印已提交且已开始执行的任务数
        System.out.printf("Main: Fork/Join Pool: Queued Tasks: %d\n",
                pool.getQueuedTaskCount());
        //打印一个布尔值表示是否有未开始执行的等待任务
        System.out.printf("Main: Fork/Join Pool: Queued Submissions: %s\n",
                pool.hasQueuedSubmissions());
        //打印任务窃取次数
        System.out.printf("Main: Fork/Join Pool: Steal Count: %d\n",
                pool.getStealCount());
        //打印线程池是否已经关闭
        System.out.printf("Main: Fork/Join Pool: Terminated: %s\n",
                pool.isTerminated());
        System.out.printf("***********************\n");
    }

}
