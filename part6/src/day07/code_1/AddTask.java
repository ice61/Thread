package day07.code_1;

import java.util.concurrent.ConcurrentLinkedDeque;

public class AddTask implements Runnable {

    //列表
    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        //设置名字为当前线程的名字
        String name = Thread.currentThread().getName();
        //向容器中加入数据
        for (int i = 0; i < 10000; i++) {
            list.add(name + " : Element " + i);
        }
    }
}
