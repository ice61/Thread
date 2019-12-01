package day08.code_05;


import java.util.Date;
import java.util.concurrent.*;

public class MyScheduledTask<V> extends FutureTask<V>
        implements RunnableScheduledFuture<V> {

    //保存ScheduledFutureTask对象
    private RunnableScheduledFuture<V> task;

    //定时执行器
    private ScheduledThreadPoolExecutor executor;

    //执行周期
    private long period;

    //开始时间
    private long startDate;

    public MyScheduledTask(Runnable runnable, V result,
                           RunnableScheduledFuture<V> task,
                           ScheduledThreadPoolExecutor executor) {
        //调用父类FutureTask的构造方法
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //非周期任务直接调用ScheduledFutureTask对象的方法
        if (!isPeriodic()) {
            return task.getDelay(unit);
        } else {
            //周期性任务但是还未执行过，直接调用ScheduledFutureTask对象的方法
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                //周期性任务并且之前执行过
                //根据自定义的属性计算出距离下一次运行的时间
                Date now = new Date();
                long delay = startDate - now.getTime();
                return unit.convert(delay, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public int compareTo(Delayed o) {
        long diff = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        //判断任务是否是周期性执行且执行器未关闭
        if (isPeriodic() && (!executor.isShutdown())) {
            //获取当前时间
            Date now = new Date();
            //计算出下一次任务的执行时间
            startDate = now.getTime() + period;
            //将任务再次加入
            executor.getQueue().add(this);
        }
        //打印任务开始执行时的日期
        System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
        //打印任务执行的周期
        System.out.printf("MyScheduledTask: Is Periodic: %s\n", isPeriodic());
        //调用FutureTask的方法来执行传入的任务并重置
        super.runAndReset();
        //打印任务结束的时间
        System.out.printf("Post-MyScheduledTask: %s\n", new Date());
    }

}
