package day03.code_1;

public class Company implements Runnable {

    //账户
    private Account account;

    //有参构造函数
    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        //循环执行100次增加余额方法
        for (int i = 0; i < 100; i++) {
            account.addAmount(1000);
        }
    }
}
