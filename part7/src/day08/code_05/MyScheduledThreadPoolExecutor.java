package day08.code_05;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledThreadPoolExecutor extends
        ScheduledThreadPoolExecutor {

    //指定核心线程数量的构造方法
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate
            (Runnable command, long initialDelay, long period, TimeUnit unit) {
        //调用父类的方法执行传入的任务
        ScheduledFuture<?> task = super.scheduleAtFixedRate
                (command, initialDelay, period, unit);
        //将返回值强转为定制的任务
        MyScheduledTask myTask = (MyScheduledTask) task;
        //设置任务执行周期
        myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
        //返回任务
        return myTask;
    }

    //装饰任务方法，此方法会在父类的scheduleAtFixedRate方法中被调用
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(
            Runnable runnable, RunnableScheduledFuture<V> task) {
        //创建我们自己的定制任务类
        //第三个参数task在这里传入的是ScheduledThreadPoolExecutor的内部类ScheduledFutureTask
        MyScheduledTask<V> myTask =
                new MyScheduledTask<>(runnable, null, task, this);
        //返回定制任务类
        return myTask;

    }
}
