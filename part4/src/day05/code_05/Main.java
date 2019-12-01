package day05.code_05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        //创建缓存执行器
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //创建一个容器
        ArrayList<Task> taskList = new ArrayList<>();
        //创建三个任务并装入容器
        for (int i = 0; i < 3; i++) {
            Task task = new Task(Integer.toString(i));
            taskList.add(task);
        }
        //声明一个装有Future对象的集合
        List<Future<Result>> resultList = null;
        try {
            //向执行器发送任务列表
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭执行器
        executor.shutdown();
        //打印结果
        System.out.println("Main: Printing the results");
        //遍历装有Future对象的集合
        for (int i = 0; i < resultList.size(); i++) {
            //得到每一个Future对象
            Future<Result> future = resultList.get(i);
            try {
                //得到结果
                Result result = future.get();
                //打印结果
                System.out.printf("%s : %s\n",
                        result.getName(), result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
