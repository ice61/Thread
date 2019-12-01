package day07.code_7;

public class Bank implements Runnable {

    //账号
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        //每次从账户中减少1000元，循环十次
        for (int i = 0; i < 10; i++) {
            this.account.substractAmount(1000);
        }
    }
}
