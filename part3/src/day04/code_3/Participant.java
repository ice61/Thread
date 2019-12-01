package day04.code_3;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {

    //会议类
    private Videoconference conference;

    //与会者姓名
    private String name;

    //通过构造函数为两个成员变量赋值
    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        //休眠随机时间表示前来参加会议所话费的时间
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //调用会议对象的arrive方法使计数器减一
        conference.arrive(name);
    }

}
