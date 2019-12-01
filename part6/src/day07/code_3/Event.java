package day07.code_3;

public class Event implements Comparable<Event> {

    //线程的编号
    private int thread;

    //优先级
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {
        //如果比传入的事件优先级高
        if (this.priority > o.getPriority()) {
            //返回<0的数表示排在传入事件的前面
            return -1;
        } else if (this.priority < o.getPriority()) {
            //返回>0的数表示排在传入事件的后面
            return 1;
        } else {
            //返回0表示和传入事件的顺序不确定
            return 0;
        }
    }
}
