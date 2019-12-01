package day04.code_6;


import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable {

    //我们自己拓展的Phaser类
    private MyPhaser myPhaser;

    public Student(MyPhaser myPhaser) {
        this.myPhaser = myPhaser;
    }

    @Override
    public void run() {
        //打印学生到达考场的信息
        System.out.printf("%s: Has arrived to do the exam.%s\n",
                Thread.currentThread().getName(), new Date());
        //通知MyPhaser对象此线程已完成第0阶段任务并进入等待状态
        myPhaser.arriveAndAwaitAdvance();
        //被唤醒，进入第1阶段
        //打印学生开始作答第一题的信息
        System.out.printf("%s: Is going to do the first exercise.%s\n",
                Thread.currentThread().getName(), new Date());
        //休眠随机时间
        doExercise();
        //打印第一题作答完毕的信息
        System.out.printf("%s: Has done the first exercise.%s\n",
                Thread.currentThread().getName(), new Date());
        //通知MyPhaser对象此线程已完成第1阶段任务并进入等待状态
        myPhaser.arriveAndAwaitAdvance();
        //被唤醒，进入第2阶段
        //打印学生开始作答第二题的信息
        System.out.printf("%s: Is going to do the second exercise.%s\n",
                Thread.currentThread().getName(), new Date());
        //休眠
        doExercise();
        //打印第二题作答完毕的信息
        System.out.printf("%s: Has done the second exercise.%s\n",
                Thread.currentThread().getName(), new Date());
        //通知MyPhaser对象此线程已完成第2阶段任务并进入等待状态
        myPhaser.arriveAndAwaitAdvance();
        //被唤醒，进入第3阶段
        //打印学生开始作答第三题的信息
        System.out.printf("%s: Is going to do the third exercise.%s\n",
                Thread.currentThread().getName(), new Date());
        //休眠
        doExercise();
        //三题均答完，考试结束
        System.out.printf("%s: Has done the third exercise.%s\n",
                Thread.currentThread().getName(), new Date());
        //等待其他线程完成当前阶段任务
        myPhaser.arriveAndAwaitAdvance();
    }

    //休眠方法
    private void doExercise() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
