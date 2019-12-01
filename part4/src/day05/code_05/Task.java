package day05.code_05;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Result> {

    //任务的名字
    private String name;

    public Task(String name) {
        this.name = name;
    }

    //这里的call方法返回一个Result对象
    @Override
    public Result call() throws Exception {
        //打印任务开始信息
        System.out.printf("%s: Starting\n", this.name);
        //休眠随机时间
        long duration = (long) (Math.random() * 10);
        System.out.printf("%s: Waiting %d seconds for results\n",
                this.name, duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建Result对象并为其成员变量赋值
        Result result = new Result();
        result.setName(this.name);
        result.setValue((int) Math.random() * 100);
        //返回result对象
        return result;
    }
}
