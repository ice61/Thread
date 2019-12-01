package day03.code_2;

public class Cinema {

    //一号厅的剩余票量
    private long vacanciesCinema1;
    //二号厅的剩余票量
    private long vacanciesCinema2;
    //两个对象作为之后同步时的传入参数
    private final Object controlCinema1, controlCinema2;

    //在构造函数中对电影院类进行初始化
    public Cinema() {
        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
        controlCinema1 = new Object();
        controlCinema2 = new Object();
    }

    //一号影厅的售票方法
    public boolean sellTickets1(int number) {
        //使用controlCinema1作为传入参数
        synchronized (controlCinema1) {
            //如果购票量小于当前剩余票量，出票
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            } else
                return false;
        }
    }

    //二号影厅的售票方法
    public boolean sellTickets2(int number) {
        //使用controlCinema2作为传入参数
        synchronized (controlCinema2) {
            //如果购票量小于当前剩余票量，出票
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            } else
                return false;
        }
    }

    //一号影厅的退票方法
    public boolean returnTickets1(int number) {
        //使用controlCinema1作为传入参数
        synchronized (controlCinema1) {
            vacanciesCinema1 += number;
            return true;
        }
    }

    //二号影厅的退票方法
    public boolean returnTickets2(int number) {
        //使用controlCinema2作为传入参数
        synchronized (controlCinema2) {
            vacanciesCinema2 += number;
            return true;
        }
    }

    //查看一号影厅剩余票量方法
    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }

    //查看二号影厅剩余票量方法
    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}
