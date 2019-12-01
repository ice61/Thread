package thread.part1.code;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
    @Override
    public void run() {
        //循环10次
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date().toString());
            try {
                //使当前线程睡眠一秒，效果等同于Thread.sleep(1000)
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public static void main(String[] args) {
        FileClock fileClock = new FileClock();
        Thread thread = new Thread(fileClock);
        thread.start();
        System.out.println("main方法执行完毕");
    }
}
