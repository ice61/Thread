package day03.code_3;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {

    //最大存储容量
    private int maxSize;

    //使用一个集合作为底层的存储空间
    private List<Date> storage;

    //通过构造函数进行初始化
    public EventStorage() {
        //设置最大存储容量为10
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    //同步的装填信息方法
    public synchronized void set() {
        /*
         * 判断存储空间是否已满
         * 已满的话进入休眠状态
         * 等待其他线程的唤醒
         * */
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //向存储空间中加入数据
        storage.add(new Date());
        //打印存储空间当前信息个数
        System.out.printf("Set: %d\n", storage.size());
        //此处是为了唤醒那些因存储空间为空而睡眠的线程
        notifyAll();
    }

    //同步的取出信息方法
    public synchronized void get() {
        /*
         * 判断存储空间是否为空
         * 为空的话进入休眠状态
         * 等待其他线程的唤醒
         * */
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印空间存储的信息个数和取出的信息
        System.out.printf("Get: %d: %s\n",
                storage.size(), storage.remove(0));
        //此处是为了唤醒那些因存储空间已满而睡眠的线程
        notifyAll();
    }
}
