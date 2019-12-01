package day08.code_04;

import day08.code_03.MyTask;
import day08.code_03.MyThreadFactory;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建定制工厂对象
        MyThreadFactory myTactory = new MyThreadFactory("MyThreadFactory");
        //创建执行器并将定制工厂对象作为参数传入
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool(myTactory);
        //创建任务
        MyTask myTask = new MyTask();
        //提交任务
        executor.submit(myTask);
        //关闭执行器
        executor.shutdown();
        //等待执行器将所有任务执行完毕
        executor.awaitTermination(1, TimeUnit.DAYS);
        //打印程序结束信息
        System.out.printf("Main: End of the program\n");

    }

}
