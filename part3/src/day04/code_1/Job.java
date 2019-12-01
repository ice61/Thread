package day04.code_1;

public class Job implements Runnable {

    //打印队列类的声明
    private PrintQueue printQueue;

    //通过构造函数传入打印队列对象的引用并初始化
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        //打印前输出相关信息
        System.out.printf("%s: Going to print a job\n",
                Thread.currentThread().getName());
        //调用打印队列的打印方法
        printQueue.printJob();
        //打印后输出相关信息
        System.out.printf("%s: The document has been printed\n",
                Thread.currentThread().getName());
    }
}
