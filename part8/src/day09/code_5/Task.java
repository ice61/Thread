package day09.code_5;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Task implements Runnable {

    @Override
    public void run() {
        //调用静态方法获取得日志生成器，并将当前类名作为参数传入
        Logger logger = MyLogger.getLogger(this.getClass().getName());
        //输出FINER级别的消息表示方法开始执行
        logger.entering(Thread.currentThread().getName(), "run()");
        //休眠两秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出FINER级别的消息表示方法执行结束
        logger.exiting(Thread.currentThread().getName(), "run()",
                Thread.currentThread());
    }
}
