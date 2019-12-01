package day05.code_10;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ReportGenerator implements Callable<String> {

    private String sender;

    private String title;

    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        //打印休眠时间后进入休眠状态
        long duration = (long) (Math.random() * 10);
        System.out.printf("%s_%s: ReportGenerator: Generating a " +
                        "report during %d seconds\n",
                this.sender, title, duration);
        TimeUnit.SECONDS.sleep(duration);
        //拼接成员变量作为返回值
        String ret = sender + ": " + title;
        return ret;

    }
}
