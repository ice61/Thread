package day05.code_08;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        //创建一个缓存执行器
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //创建任务
        Task task = new Task();
        //打印执行任务提示信息
        System.out.printf("Main: Executing the Task\n");
        //执行任务并得到Future对象
        Future<String> result = executor.submit(task);
        //当前线程休眠2秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印取消任务提示信息
        System.out.printf("Main: Canceling the Task\n");
        //取消任务
        result.cancel(true);
        //打印任务是否被取消
        System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
        //打印任务是否完成
        System.out.printf("Main: Done: %s\n", result.isDone());
        //关闭执行器
        executor.shutdown();
        //打印执行器关闭信息
        System.out.printf("Main: The executor has finished\n");
    }

}
