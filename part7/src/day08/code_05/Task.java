package day08.code_05;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    @Override
    public void run() {
        //打印任务开始提示语
        System.out.printf("Task: Begin\n");
        //休眠2秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印任务结束提示语
        System.out.printf("Task: End\n");
    }
}
