package day03.code_4;

public class Job implements Runnable {

    //打印队列对象
    private PrintQueue printQueue;

    //有参构造函数将被传入一个创建好的打印队列对象引用
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        //打印开始提示语
        System.out.printf("%s: Going to print a document\n",
                Thread.currentThread().getName());
        //调用打印队列的方法，此方法已使用锁进行同步
        printQueue.printJob();
        //打印结束提示语
        System.out.printf("%s: The document has been printed\n",
                Thread.currentThread().getName());
    }
}
