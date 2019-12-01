package day08.code_06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask extends RecursiveTask<Integer> {

    //超大数组
    private int array[];

    //任务起始、终止位置
    private int start, end;

    //构造方法
    public MyRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //初始化结果
        int ret = 0;
        //获取当前线程
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        //调用线程的addTask方法增加任务计数器的值
        thread.addTask();
        //如果任务过大则分解
        if (end - start > 10000) {
            int middle = (start + end) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(array, start, middle);
            MyRecursiveTask task2 = new MyRecursiveTask(array, middle, end);
            //异步执行任务
            task1.fork();
            task2.fork();
            //合并结果
            return addResults(task1, task2);
        }
        //求出范围内数组的和
        for (int i = start; i < end; i++) {
            ret += array[i];
        }
        //返回结果
        return ret;

    }

    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value;
        //尝试获取两个任务的返回值
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //返回结果值
        return value;
    }
}
