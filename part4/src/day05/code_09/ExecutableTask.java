package day05.code_09;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExecutableTask implements Callable<String> {

    //任务名称
    private String name;

    public String getName() {
        return name;
    }

    public ExecutableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        //获取休眠时间
        long duration = (long) (Math.random() * 10);
        //打印相关信息
        System.out.printf("%s: Waiting %d seconds for results\n",
                this.name, duration);
        //休眠
        TimeUnit.SECONDS.sleep(duration);
        //返回字符串类型的结果
        return "Hello, world. I'm " + this.name;
    }
}
