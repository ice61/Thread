package day07.code_7;

public class Company implements Runnable {

    //账号
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        //每次向a账户中增加1000元，循环十次
        for (int i = 0; i < 10; i++) {
            account.addAmount(1000);
        }
    }
}
