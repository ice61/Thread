package day08.code_03;

public class Main {

    public static void main(String[] args) {
        //创建定制的线程工厂
        MyThreadFactory myTactory = new MyThreadFactory("MyThreadFactory");
        //创建任务
        MyTask myTask = new MyTask();
        //创建定制的线程对象
        Thread thread = myTactory.newThread(myTask);
        //开启线程
        thread.start();
        //等待线程执行结束
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印线程执行结束信息
        System.out.printf("Main: Thread information\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example\n");
    }

}
