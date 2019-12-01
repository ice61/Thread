package day04.code_2;

import day04.code_1.PrintQueue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue1 extends PrintQueue {

    /*
     * 布尔类型的数组
     * 用来存放打印机的状态
     * true为空闲，false为忙碌
     * */
    private boolean freePrinters[];

    //用来同步操作上面数组的代码的锁
    private Lock lockPrinters;

    //信号量
    private Semaphore semaphore;

    public PrintQueue1() {
        //这里设置最大同时可访问线程数为3
        semaphore = new Semaphore(3);
        //假设我们有三台打印机
        freePrinters = new boolean[3];
        //还没有开始工作，所以三台打印机均空闲
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        //创建一个锁对象
        lockPrinters = new ReentrantLock();
    }

    public void printJob() {
        try {
            //获得信号量，此方法会抛出异常
            semaphore.acquire();
            //获取选中的打印机编号
            int assignedPrinter = getPrinter();
            //打印相关信息后休眠随机的时间
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue1: Printing a Job in %d Printer" +
                            "during " + "%d seconds\n",
                    Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);
            //打印完成后将打印机状态重新置为空闲
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放信号量
            semaphore.release();
        }
    }

    //查找空闲打印机的方法
    private int getPrinter() {
        //初始化查询结果变量
        int ret = -1;
        try {
            //获取锁
            lockPrinters.lock();
            //遍历打印机状态数组
            for (int i = 0; i < freePrinters.length; i++) {
                //找到第一个状态为空闲的打印机
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lockPrinters.unlock();
        }
        //返回空闲的打印机编号
        return ret;
    }
}
