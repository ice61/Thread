package day03.code_5;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {

    //双精度浮点型的价格1
    private double price2;

    //双精度浮点型的价格2
    private double price1;

    //读写锁
    private final ReentrantReadWriteLock lock;

    /*
     * 构造函数
     * 设置价格1为1.0，价格2为2.0
     * */
    public PricesInfo() {
        this.price1 = 1.0;
        this.price2 = 2.0;
        this.lock = new ReentrantReadWriteLock();
    }

    //查询价格1方法
    public void getPrice1() {
        //获取读操作锁并加锁
        lock.readLock().lock();
        //得到价格后打印相关信息
        double value = this.price1;
        System.out.printf("%s: Price 1: %.2f\n",
                Thread.currentThread().getName(),
                value);
        //释放读操作锁
        lock.readLock().unlock();
    }

    //查询价格2方法
    public void getPrice2() {
        //获取读操作锁并加锁
        lock.readLock().lock();
        //得到价格后打印相关信息
        double value = this.price2;
        System.out.printf("%s: Price 2: %.2f\n",
                Thread.currentThread().getName(),
                value);
        //释放读操作锁
        lock.readLock().unlock();
    }

    //修改价格方法
    public void setPrice(double price1, double price2) {
        //获取写操作锁并加锁
        lock.writeLock().lock();
        //修改价格后打印相关信息
        this.price1 = price1;
        this.price2 = price2;
        System.out.printf("Price has been changed price1: %.2f price2: %.2f\n",
                price1, price2);
        //释放写操作锁
        lock.writeLock().unlock();
    }

}
