package day07.code_1;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable {

    //列表
    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        //循环5000次每次取出两个数据
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
