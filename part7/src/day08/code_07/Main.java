package day08.code_07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建数组，元素默认都为0
        int[] array = new int[10000];
        //创建线程池
        ForkJoinPool pool = new ForkJoinPool();
        //创建任务
        Task task = new Task("Task", array, 0, array.length);
        //同步执行任务
        pool.invoke(task);
        //关闭线程池
        pool.shutdown();
        //等待线程池执行完所有任务后关闭
        pool.awaitTermination(1, TimeUnit.DAYS);
        //检查任务是否正常完成了
        //因为最初元素都为0，正确自增之后应该为1
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 1) {
                System.out.println("Error!");
            }
        }
        //打印程序结束提示语
        System.out.printf("Main: End of the program\n");
    }

}
