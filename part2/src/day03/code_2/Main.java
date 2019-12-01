package day03.code_2;

public class Main {

    public static void main(String[] args) {
        //创建一个电影院类对象
        Cinema cinema = new Cinema();
        //创建售票处对象并将其作为参数传入线程对象的构造函数中
        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");
        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");
        //开启两个线程
        thread1.start();
        thread2.start();
        try {
            //当前线程挂起，等待两个线程的执行结束
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //分别打印两个放映厅的剩余票量
        System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());
    }

}
