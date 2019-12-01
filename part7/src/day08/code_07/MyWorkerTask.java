package day08.code_07;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

public abstract class MyWorkerTask extends ForkJoinTask<Void> {

    //任务名称
    private String name;

    //构造方法
    public MyWorkerTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Void getRawResult() {
        //因为当前任务无返回值，所以返回null
        return null;
    }

    @Override
    protected void setRawResult(Void value) {
        //因为无返回值，所以方法为空
    }

    @Override
    protected boolean exec() {
        //创建任务开始时间
        Date startDate = new Date();
        //开始执行任务
        compute();
        //创建任务执行结束时间
        Date finishDate = new Date();
        //计算时间差
        long diff = finishDate.getTime() - startDate.getTime();
        //打印任务执行所花费的时间
        System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete\n",
                name, diff);
        return true;
    }

    protected abstract void compute();
}
