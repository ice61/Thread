package day05.code_10;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建一个缓存执行器
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        /*
         * 创建一个ExecutorCompletionService对象
         * 因为执行任务需要依赖于执行器，所以将创建好的执行器的引用传入
         * */
        ExecutorCompletionService<String> service =
                new ExecutorCompletionService<>(executor);
        /*
         * 创建了两个ReportRequest对象并作为参数传入线程的构造函数中
         * 这两个线程用于向ExecutorCompletionService对象中发送任务
         * */
        ReportRequest faceRequest = new ReportRequest("Face", service);
        Thread faceThread = new Thread(faceRequest);
        ReportRequest onlineRequest = new ReportRequest("Online", service);
        Thread onlineThread = new Thread(onlineRequest);
        /*
         * 创建一个ReportProcessor对象并作为参数传入线程的构造函数中
         * 这个线程用于从ExecutorCompletionService中取出Future对象
         * */
        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);
        //程序开始提示语
        System.out.println("Main: Starting the Threads");
        //启动创建好的三个线程
        faceThread.start();
        onlineThread.start();
        senderThread.start();
        //主线程挂起直到发送任务的线程运行结束
        try {
            System.out.printf("Main: Waiting for the report generators\n");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印关闭执行器提示语
        System.out.printf("Main: Shutting down the executor\n");
        //关闭执行器
        executor.shutdown();
        //等待执行器执行完所有任务
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结束用于获取Future对象的线程
        processor.setEnd(true);
        //打印程序结束提示语
        System.out.println("Main: Ends");
    }

}
