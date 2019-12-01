package day08.code_06;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkerThread extends ForkJoinWorkerThread {

    //线程级别的计数器
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

    //构造方法
    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    @Override
    protected void onStart() {
        //必须先调用父类的onStart方法
        super.onStart();
        //打印线程信息
        System.out.printf("MyWorkerThread %d: Initializing task counter\n",
                getId());
        //初始化任务计数器
        taskCounter.set(0);
    }

    @Override
    protected void onTermination(Throwable exception) {
        //打印线程信息和执行的任务数
        System.out.printf("MyWorkerThread %d: %d\n",
                getId(), taskCounter.get());
        //必须在最后调用父类的onTermination方法
        super.onTermination(exception);
    }

    //调用此方法可以改变任务计数器的值
    public void addTask() {
        //得到计数器的值
        int counter = taskCounter.get().intValue();
        //自增
        counter++;
        //更新计数器的值
        taskCounter.set(counter);
    }

}
