package day08.code_01;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SleepTwoSecondsTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        //休眠2秒
        TimeUnit.SECONDS.sleep(2);
        //返回时间字符串
        return new Date().toString();
    }
}
