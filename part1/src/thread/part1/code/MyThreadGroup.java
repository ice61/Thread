package thread.part1.code;

public class MyThreadGroup extends ThreadGroup {

    //必须给出带有参数的构造函数
    public MyThreadGroup(String name) {
        super(name);
    }

    //重写uncaughtException方法
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //中断剩余的线程
        this.interrupt();
        System.out.println("Terminating the rest of the Threads\n");
        //打印抛出异常的线程
        System.out.printf("The thread %s has thrown an Exception\n",t.getId());
        //打印堆栈记录
        e.printStackTrace(System.out);
    }

    public static void main(String[] args) {
        //创建线程组对象
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        //创建错误任务类
        ErrorTask errorTask = new ErrorTask();
        //创建并执行三个线程
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(threadGroup, errorTask);
            thread.start();
        }
    }
}
