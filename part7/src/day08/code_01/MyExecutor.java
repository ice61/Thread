package day08.code_01;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyExecutor extends ThreadPoolExecutor {

    //存储任务开始时间的map
    private ConcurrentHashMap<String, Date> startTime;

    //覆盖构造方法
    public MyExecutor(int corePoolSize, int maximumPoolSize,
                      long keepAliveTime, TimeUnit unit,
                      BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTime = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        //在shutdown方法中输出线程池相关信息
        System.out.printf("MyExecutor: Going to shutdown\n");
        //执行完毕的任务数量
        System.out.printf("MyExecutor: Executed tasks: %d\n",
                getCompletedTaskCount());
        //正在执行的任务数量
        System.out.printf("MyExecutor: Running tasks: %d\n",
                getActiveCount());
        //等待执行的任务数量
        System.out.printf("MyExecutor: Pending tasks: %d\n",
                getQueue().size());
        //调用父类的shutdown方法
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        //在shutdownNow方法中输出线程池相关信息
        System.out.printf("MyExecutor: Going to immediately shutdown\n");
        //执行完毕的任务数量
        System.out.printf("MyExecutor: Executed tasks: %d\n",
                getCompletedTaskCount());
        //正在执行的任务数量
        System.out.printf("MyExecutor: Running tasks: %d\n",
                getActiveCount());
        //等待执行的任务数量
        System.out.printf("MyExecutor: Pending tasks: %d\n",
                getQueue().size());
        //调用父类的shutdownNow方法
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //在任务开始执行前打印线程名称和任务的哈希码
        System.out.printf("MyExecutor: A task is beginning: %s: %s\n",
                t.getName(), r.hashCode());
        //以任务哈希码为键，日期为值，装入map中
        startTime.put(String.valueOf(r.hashCode()), new Date());
        //调用父类的beforeExecute方法
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        //对任务进行类型强转
        Future<?> result = (Future<?>) r;
        try {
            //打印任务结束提示语
            System.out.println("*****************************");
            System.out.println("MyExecutor: A task is finishing");
            //打印结果
            System.out.printf("MyExecutor: Result: %s\n", result.get());
            //计算执行所花费的时间
            Date startDate = startTime.remove(String.valueOf(r.hashCode()));
            Date finishDate = new Date();
            long diff = finishDate.getTime() - startDate.getTime();
            //打印执行所花费的时间
            System.out.printf("MyExecutor: Duration: %d\n", diff);
            System.out.println("*****************************");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //调用父类方法
        super.afterExecute(r, t);
    }


}
