package day08.code_02;

import java.util.concurrent.TimeUnit;

public class MyPriorityTask implements Runnable,
        Comparable<MyPriorityTask> {

    //优先级
    private int priority;

    //任务名称
    private String name;

    public MyPriorityTask(String name, int priority) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        //如果优先级较高，排在队列靠前位置
        if (this.getPriority() > o.getPriority()) {
            return -1;
            //优先级较低排在靠后位置
        } else if (this.getPriority() < o.getPriority()) {
            return 1;
        }
        //优先级相同则没有明确顺序
        return 0;
    }

    @Override
    public void run() {
        //打印任务名称和优先级
        System.out.printf("MyPriorityTask: %s Priority : %d\n",
                name, priority);
        //休眠两秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
