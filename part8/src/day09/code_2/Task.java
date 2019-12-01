package day09.code_2;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    //休眠的时间
    private int time;

    //phaser对象
    private Phaser phaser;

    //通过构造函数赋值
    public Task(int time, Phaser phaser) {
        this.time = time;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        //通知phaser对象线程以完成当前阶段并直接向下执行
        phaser.arrive();
        //打印进入第一阶段提示信息
        System.out.printf("%s: Entering phase 1\n",
                Thread.currentThread().getName());
        //休眠
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印线程完成第一阶段提示信息
        System.out.printf("%s: Finishing phase 1\n",
                Thread.currentThread().getName());
        //通知phaser对象线程以完成当前阶段并等待
        phaser.arriveAndAwaitAdvance();
        //打印进入第二阶段提示信息
        System.out.printf("%s: Entering phase 2\n",
                Thread.currentThread().getName());
        //休眠
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印线程完成第二阶段提示信息
        System.out.printf("%s: Finishing phase 2\n",
                Thread.currentThread().getName());
        //通知phaser对象线程以完成当前阶段并等待
        phaser.arriveAndAwaitAdvance();
        //打印进入第三阶段提示信息
        System.out.printf("%s: Entering phase 3\n",
                Thread.currentThread().getName());
        //休眠
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印完成第三阶段提示信息
        System.out.printf("%s: Finishing phase 3\n",
                Thread.currentThread().getName());
        //通知phaser对象线程以完成当前阶段并取消注册
        phaser.arriveAndDeregister();
    }
}
