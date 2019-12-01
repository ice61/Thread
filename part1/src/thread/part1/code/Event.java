package thread.part1.code;

import java.util.Date;

public class Event {
    //日期，之后用来判断创建的时间
    private Date date;
    //字符串类型的描述
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
