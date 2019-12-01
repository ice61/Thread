package day04.code_3;

public class Main {

    public static void main(String[] args) {
        //创建一个会议类对象，并设置需要等待的线程数为10
        Videoconference conference = new Videoconference(10);
        //以此为参数创建一个线程并开启
        Thread threadConference = new Thread(conference);
        threadConference.start();

        //循环创建10个与会者对象，以此作为参数创建线程并开启
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(conference, "Participant " + i);
            Thread thread = new Thread(participant);
            thread.start();
        }
    }

}
