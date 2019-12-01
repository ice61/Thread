package day06.code_4;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Task extends RecursiveTask<Integer> {

    //数组
    private int[] array;

    //起始、终止位置
    private int start, end;

    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //打印搜索范围的信息
        System.out.printf("Task: Start from %d to %d\n",
                start, end);
        //如果搜索范围小于10
        if (end - start < 10) {
            //判断是否包含索引三
            if ((3 > start) && (3 < end)) {
                //抛出运行时异常
                throw new RuntimeException("This task throws an Exception: " +
                        "Task from " + start + " to " + end);
            }
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            //分割任务
            int mid = (start + end) / 2;
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);
            //执行
            invokeAll(task1, task2);
        }
        //打印任务结束语
        System.out.printf("Task: End from %d to %d\n", start, end);
        return 0;

    }
}
