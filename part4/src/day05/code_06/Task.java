package day05.code_06;

import java.util.Date;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    //任务名称
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        //打印任务开始信息
        System.out.printf("%s: Starting at : %s\n",
                this.name, new Date());
        return "Hello, world";
    }
}
