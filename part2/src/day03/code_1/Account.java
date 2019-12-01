package day03.code_1;

public class Account {

    //双精度浮点型的余额
    private double balance;
    //余额的set、get方法
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //使用synchronized修饰的增加余额方法
    public synchronized void addAmount(double amount) {
        //休眠10ms
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //余额增加
        balance += amount;
    }
    //使用synchronized修饰的减少余额方法
    public synchronized void subtractAmount(double amount) {
        //休眠10秒
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //余额减少
        balance -= amount;
    }


}
