package day09.code_4;

import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

    //数组
    private int[] array;

    //任务搜索的起始、终止 位置
    private int start, end;

    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //如果任务过大
        if (end - start > 100) {
            //进行拆解
            int mid = (start + end) / 2;
            //创建新任务
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);
            //异步执行任务
            task1.fork();
            task2.fork();
            //等待任务执行结束
            task1.join();
            task2.join();
        } else {
            //在指定范围内遍历数组
            for (int i = start; i < end; i++) {
                //自增
                array[i]++;
                //休眠5毫秒
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
