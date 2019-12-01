package day09.code_5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        //通过静态方法获取日志生成器
        Logger logger = MyLogger.getLogger("Core");
        //输出FINER级别的消息表示方法开始执行
        logger.entering("Core", "main()", args);
        //创建数组
        Thread[] threads = new Thread[5];
        //遍历数组
        for (int i = 0; i < threads.length; i++) {
            //输出INFO级别的日志
            logger.log(Level.INFO, "Launching thread: " + i);
            //创建任务和线程对象
            Task task = new Task();
            threads[i] = new Thread(task);
            //输出INFO级别的日志
            logger.log(Level.INFO, "Thread created: " + threads[i].getName());
            //开启线程
            threads[i].start();
        }
        //输出日志
        logger.log(Level.INFO, "Five Threads created.");
        logger.log(Level.INFO, "Waiting for its finalization");
        //等待任务运行结束
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
                //打印日志
                logger.log(Level.INFO, "Thread has finished its execution", threads[i]);
            } catch (InterruptedException e) {
                //打印出现异常的日志
                logger.log(Level.SEVERE, "Exeception", e);
            }

        }
        //打印方法结束的日志
        logger.exiting("Core", "main()");
    }

}
