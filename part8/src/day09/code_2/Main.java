package day09.code_2;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建phaser对象
        Phaser phaser = new Phaser(3);
        //循环创建三个任务并开启线程运行它们
        for (int i = 0; i < 3; i++) {
            //通过循环次数为其设置不同的休眠时间
            Task task = new Task(i + 1, phaser);
            Thread thread = new Thread(task);
            thread.start();
        }
        //循环十次
        for (int i = 0; i < 10; i++) {
            System.out.printf("********************\n");
            //打印提示信息
            System.out.printf("Main: Phaser Log\n");
            //打印phaser的当前阶段
            System.out.printf("Main: Phaser: Phase: %d\n",
                    phaser.getPhase());
            //打印在phaser对象上注册的任务数
            System.out.printf("Main: Phaser: Registered Parties: %d\n",
                    phaser.getRegisteredParties());
            //打印已结束当前阶段的任务数
            System.out.printf("Main: Phaser: Arrived Parties: %d\n",
                    phaser.getArrivedParties());
            //打印未结束当前阶段的任务数
            System.out.printf("Main: Phaser: Unarrived Parties: %d\n",
                    phaser.getUnarrivedParties());
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
