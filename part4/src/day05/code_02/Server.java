package day05.code_02;

import day05.code_01.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    //线程池执行器
    private ThreadPoolExecutor executor;

    public Server() {
        /*
         * 通过Executors的工厂方法创建一个固定大小为5的线程执行器
         * 此方法返回的是一个ExecutorService，因此我们要向下转型
         * */
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task) {
        //打印任务到达提示语
        System.out.printf("Server: A new task has arrived\n");
        //使用execute方法发送Runnable类型的任务
        executor.execute(task);
        //打印执行器线程池中的线程数
        System.out.printf("Server: Pool Size: %d\n",
                executor.getPoolSize());
        //打印线程池中正在执行任务的线程数
        System.out.printf("Server: Active Count: %d\n",
                executor.getActiveCount());
        //打印已提交给执行器的任务数
        System.out.printf("Server: Task Count: %d\n",
                executor.getTaskCount());
        //打印已完成的任务数
        System.out.printf("Server: Completed Tasks: %d\n",
                executor.getCompletedTaskCount());
    }

    public void endServer() {
        //结束线程池执行器
        executor.shutdown();
    }

}
