package day05.code_04;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {

    //登录名
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean validate() {
        //创建一个随机数生成器对象
        Random random = new Random();
        //休眠随机时间
        long duration = (long) (Math.random() * 10);
        System.out.printf("Validator %s: Validating a user during %d seconds\n",
                this.name, duration);
        //如果线程在休眠期间被中断就返回false
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }
        //休眠结束后随机返回一个结果
        return random.nextBoolean();
    }
}
