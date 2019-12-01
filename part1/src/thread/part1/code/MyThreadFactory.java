package thread.part1.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    //创建线程类对象的数量
    private int counter;

    //线程工厂对象的名称
    private String name;

    //装有线程对象信息的容器
    private List<String> stats;

    //构造函数
    public MyThreadFactory(String name) {
        this.name = name;
        this.counter = 0;
        this.stats = new ArrayList<>();
    }

    public String getStats() {
        //创建字符串缓冲区
        StringBuffer stringBuffer = new StringBuffer();
        for (String stat : stats) {
            /*
            * 将字符串添加进字符串缓冲区
            * 相比于直接使用'+'拼接，这样可以减少垃圾常量的产生
            * */
            stringBuffer.append(stat);
            stringBuffer.append("\n");
        }
        //返回字符串
        return stringBuffer.toString();
    }

    @Override
    public Thread newThread(Runnable r) {
        //为新创建的线程对象添加名字
        Thread thread = new Thread(r, name + "-Thread_" + counter);
        //计数器的值增加
        counter++;
        //将新创建线程的信息装入容器
        stats.add(String.format("Created thread %d with name %s on %s\n",
                thread.getId(), thread.getName(),new Date()));
        //返回创建好的线程对象
        return thread;
    }

}
