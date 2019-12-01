package day07.code_7;

import java.util.concurrent.atomic.AtomicLong;

public class Account {

    //原子变量
    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    //返回原子变量的值
    public long getBalance() {
        return balance.get();
    }

    //设置原子变量的值
    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    //增加金额
    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }

    //减少金额
    public void substractAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }

}
