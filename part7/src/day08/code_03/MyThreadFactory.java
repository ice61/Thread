package day08.code_03;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    //计数器
    private int counter;

    //名称前缀
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        //创建线程，名字为前缀加计数器数字
        MyThread myThread = new MyThread(r, prefix + "-" + counter);
        //计数器自增
        counter++;
        //返回创建好的线程
        return myThread;
    }
}
