package day07.code_4;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Event implements Delayed {

    //预定的开始时间
    private Date startDate;

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //当前事件
        Date now = new Date();
        //当前时间和预定时间的差
        long diff = startDate.getTime() - now.getTime();
        //转为指定的时间格式
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        //比较当前事件和传入事件的时间差
        long result = this.getDelay(TimeUnit.NANOSECONDS)
                - o.getDelay(TimeUnit.NANOSECONDS);
        //当前事件的时间差较小
        if (result < 0) {
            //返回<0的数表示排在传入事件前面
            return -1;
        } else if (result > 0) {
            //返回>0的数表示排在传入事件后面
            return 1;
        }
        //不做排序
        return 0;
    }
}
