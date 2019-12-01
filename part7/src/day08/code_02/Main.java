package day08.code_02;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建执行器，任务队列使用优先级队列
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 2,
                1, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>());
        //创建四个任务
        for (int i = 0; i < 4; i++) {
            //通过构造方法设置任务名称和优先级
            MyPriorityTask task = new MyPriorityTask("Task" + i, i);
            //将任务发送到执行器
            executor.execute(task);
        }
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //再次创建四个任务
        for (int i = 4; i < 8; i++) {
            //通过构造方法设置任务名称和优先级
            MyPriorityTask task = new MyPriorityTask("Task" + i, i);
            //将任务发送到执行器
            executor.execute(task);
        }
        //关闭执行器
        executor.shutdown();
        //等待执行器将所有任务执行完毕
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印程序结束信息
        System.out.printf("Main: End of the program\n");
    }

}
