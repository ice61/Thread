package day05.code_09;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建缓存执行器
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //创建继承了FutureTask类型的数组
        ResultTask[] resultTasks = new ResultTask[5];
        //遍历数组
        for (int i = 0; i < resultTasks.length; i++) {
            //创建任务类对象
            ExecutableTask executableTask = new ExecutableTask("Task " + i);
            //创建ResultTask对象并将任务类对象作为参数
            resultTasks[i] = new ResultTask(executableTask);
            //发送到执行器
            executor.submit(resultTasks[i]);
        }
        //当前线程休眠5秒
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //取消所有任务
        for (int i = 0; i < resultTasks.length; i++) {
            resultTasks[i].cancel(true);
        }
        //打印未取消任务的结果
        for (int i = 0; i < resultTasks.length; i++) {
            try {
                if (!resultTasks[i].isCancelled()) {
                    System.out.printf("%s\n", resultTasks[i].get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭执行器
        executor.shutdown();
    }

}
