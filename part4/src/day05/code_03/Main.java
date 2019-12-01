package day05.code_03;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        //创建一个最大线程数量为2的执行器
        ThreadPoolExecutor excutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        //装载返回的Future对象的集合
        ArrayList<Future<Integer>> resultList = new ArrayList<>();
        //创建随机数生成器对象
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            //生成一个随机数
            int number = random.nextInt(10);
            //创建一个计算阶乘的任务
            FactorialCalculator calculator = new FactorialCalculator(number);
            //将任务发送给执行器并得到一个Future对象
            Future<Integer> result = excutor.submit(calculator);
            //将Future对象装入集合
            resultList.add(result);
        }
        do {
            //打印执行器已完成的任务数量
            System.out.printf("Main: Number of Completed Tasks:%d\n",
                    excutor.getCompletedTaskCount());
            //通过Future对象判断任务是否已经结束并打印
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n",
                        i, result.isDone());
            }
            //每检查一次后线程休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //一直循环直到所有的任务均完成
        } while (excutor.getCompletedTaskCount() < resultList.size());
        //打印结果提示信息
        System.out.printf("Main: Results\n");
        //遍历集合
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            //从Future对象中得到返回的结果
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //打印
            System.out.printf("Main: Task %d: %d\n",
                    i, number);
        }
        //关闭执行器
        excutor.shutdown();
    }

}
