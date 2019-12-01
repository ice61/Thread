package day08.code_01;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建自定义的ThreadPoolExecutor对象
        MyExecutor myExecutor = new MyExecutor
                (2, 4, 1000,
                        TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        //创建装载Future对象的集合
        ArrayList<Future<String>> results = new ArrayList<>();
        //发送十个任务
        for (int i = 0; i < 10; i++) {
            //创建任务
            SleepTwoSecondsTask task = new SleepTwoSecondsTask();
            //将任务发送给执行器
            Future<String> result = myExecutor.submit(task);
            //将得到的Future对象装入集合
            results.add(result);
        }
        //尝试获取前5个任务的结果
        for (int i = 0; i < 5; i++) {
            try {
                //得到任务执行结束后返回的结果
                String result = results.get(i).get();
                //打印任务编号及结果
                System.out.printf("Main: Result for Task %d : %s\n",
                        i, result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭执行器
        myExecutor.shutdown();
        //尝试获取后5个任务的结果
        for (int i = 5; i < 10; i++) {
            try {
                //得到任务执行结束后返回的结果
                String result = results.get(i).get();
                //打印任务编号及结果
                System.out.printf("Main: Result for Task %d : %s\n",
                        i, result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //等待执行器关闭
        try {
            myExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印程序结束提示语
        System.out.printf("Main: End of the program\n");
    }

}
