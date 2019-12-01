package day08.code_03;

import java.util.Date;

public class MyThread extends Thread {

    //线程创建时间
    private Date creationDate;
    //线程开始执行时间
    private Date startDate;
    //线程执行结束时间
    private Date finishDate;

    //重写构造函数
    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    @Override
    public void run() {
        //设置开始时间
        setStartDate();
        //执行任务
        super.run();
        //设置结束时间
        setFinishDate();
    }

    //设置线程被创建的时间
    public void setCreationDate() {
        creationDate = new Date();
    }

    //设置线程开始执行的时间
    public void setStartDate() {
        startDate = new Date();
    }

    //设置线程执行结束的时间
    public void setFinishDate() {
        finishDate = new Date();
    }

    //获取线程执行任务所消耗的时间
    public long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    //重写toString方法
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //线程名称
        builder.append(getName());
        builder.append(" : ");
        //创建时间
        builder.append("Creation Date: ");
        builder.append(creationDate);
        //运行时长
        builder.append(" Running time: ");
        builder.append(getExecutionTime());
        builder.append(" Milliseconds");
        return builder.toString();
    }
}
