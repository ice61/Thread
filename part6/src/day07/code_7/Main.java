package day07.code_7;

public class Main {

    public static void main(String[] args) {
        //创建一个账号，设置初始金额为1000元
        Account account = new Account();
        account.setBalance(1000);
        //创建一个公司类和线程，负责增加金额
        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        //创建一个银行类和线程，负责减少金额
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);
        //打印账号初始金额
        System.out.printf("Account : Initial Balance: %d\n",
                account.getBalance());
        //开启线程
        companyThread.start();
        bankThread.start();
        try {
            //等待线程结束运行
            companyThread.join();
            bankThread.join();
            //打印账号最终金额
            System.out.printf("Account : Final Balance: %d\n",
                    account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
