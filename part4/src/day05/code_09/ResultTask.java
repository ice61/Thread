package day05.code_09;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {

    //名称
    private String name;

    //此构造方法必须给出
    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask) callable).getName();
    }

    //重写done方法
    @Override
    protected void done() {
        //如果任务是被取消的，则打印取消信息
        if (isCancelled()) {
            System.out.printf("%s: Has been canceled\n", name);
        } else {
            //否则打印执行结束信息
            System.out.printf("%s: Has finished\n", name);
        }
    }
}
