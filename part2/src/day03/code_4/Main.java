package day03.code_4;

public class Main {
    public static void main(String[] args) {
        //创建一个打印队列对象
        PrintQueue printQueue = new PrintQueue();
        //创建一个工作对象
        Job job = new Job(printQueue);
        //循环十次已工作对象为参数创建线程并运行
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(job, "Thread" + i);
            thread.start();
        }
    }
}
