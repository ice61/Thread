package thread.part1.code;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    @Override
    public void run() {
        //编写可以抛出运行时异常的语句
        int number = Integer.parseInt("TTT");
    }

    public static void main(String[] args) {
        //创建Task类并创建线程
        Task task = new Task();
        Thread thread = new Thread(task);
        //设置线程的未捕获异常处理器为我们自己编写的类
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        //Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        //开启线程
        thread.start();
    }
}
