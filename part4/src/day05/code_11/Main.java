package day05.code_11;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        //创建自定义类
        RejectedTaskController controller = new RejectedTaskController();
        //创建缓存执行器
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //设置被拒绝任务的处理程序
        executor.setRejectedExecutionHandler(controller);
        //打印程序开始的提示信息
        System.out.println("Main: Starting");
        //向执行器发送三个任务
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task " + i);
            System.out.println(task);
            executor.submit(task);
        }
        //打印提示信息并关闭执行器
        System.out.println("Main: Shutting down the executor");
        executor.shutdown();
        //打印提示信息并再次提交一个任务
        System.out.println("Main: Sending another Task");
        Task task = new Task("RejectedTask");
        executor.submit(task);
        //打印当前线程结束信息
        System.out.println("Main: End");
    }

}
