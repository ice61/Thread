package day06.code_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建文档生成器
        DocumentMock mock = new DocumentMock();
        //生成文档
        String[][] document = mock.generateDocument(100, 1000, "the");
        //创建文档搜素任务
        DocumentTask task = new DocumentTask(document, 0, 100, "the");
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //异步执行文档搜索任务
        pool.execute(task);
        //每隔一秒打印一次线程池的状态直到任务执行结束
        do {
            System.out.println("****************************************");
            //并行级别
            System.out.printf("Main: Parallelism: %d\n",
                    pool.getParallelism());
            //正在工作的线程
            System.out.printf("Main: Active Threads: %d\n",
                    pool.getActiveThreadCount());
            //已提交的任务数量（不包括尚未执行的）
            System.out.printf("Main: Task Count: %d\n",
                    pool.getQueuedTaskCount());
            //窃取工作的数量
            System.out.printf("Main: Steal Count: %d\n",
                    pool.getStealCount());
            System.out.println("****************************************");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        //关闭线程池
        pool.shutdown();
        //打印待查找关键词的数量
        try {
            System.out.printf("Main: The word appears %d in the document",
                    task.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
