package day07.code_6;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {

    //并发随机数生成器
    private ThreadLocalRandom random;

    @Override
    public void run() {
        //得到当前线程的名字
        String name = Thread.currentThread().getName();
        //为随机数生成器赋值
        random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            //打印线程信息和生成的随机数
            System.out.printf("%s: %d\n", name, this.random.nextInt(10));
        }
    }
}
