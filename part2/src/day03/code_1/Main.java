package day03.code_1;

public class Main {

    public static void main(String[] args) {
        //创建一个账户类对象
        Account account = new Account();
        //设置账户的初始余额为1000元
        account.setBalance(1000);
        //创建一个公司类对象并以此作为参数传入创建的线程对象中
        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        //创建一个银行类对象并以此作为参数传入创建的线程对象中
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);
        //在控制台打印账户的初始金额
        System.out.printf("Account : Initial Balance: %.2f\n",account.getBalance());
        //开启两个线程对象
        companyThread.start();
        bankThread.start();
        try {
            //主线程等待两个线程的结束
            companyThread.join();
            bankThread.join();
            //结束后打印账户最后的余额
            System.out.printf("Account : Final Balance: %.2f\n",account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
