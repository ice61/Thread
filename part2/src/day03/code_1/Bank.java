package day03.code_1;

public class Bank implements Runnable {

    //账户
    private Account account;

    //有参构造方法
    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        //循环执行100次的减少余额方法
        for (int i = 0; i < 100; i++) {
            account.subtractAmount(1000);
        }
    }
}
