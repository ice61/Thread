package day09.code_3;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    //休眠时间
    private long milliseconds;

    public Task(long milliseconds) {
        //通过构造函数为休眠时间赋值
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        //打印任务开始执行的提示信息
        System.out.printf("%s: Begin\n",
                Thread.currentThread().getName());
        //休眠
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印任务执行结束的提示信息
        System.out.printf("%s: End\n",
                Thread.currentThread().getName());
    }
}
